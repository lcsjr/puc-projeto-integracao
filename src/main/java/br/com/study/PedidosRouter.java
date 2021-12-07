package br.com.study;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.ExchangePattern;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.language.xpath.XPathBuilder;
import org.springframework.stereotype.Component;

import br.com.study.enrich.ProdutoAgregationStrategy;
import br.com.study.model.Pedido;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PedidosRouter extends RouteBuilder {
	
	@Override
    public void configure() throws Exception {

		AggregationStrategy aggregationStrategy = new ProdutoAgregationStrategy();
		ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		from("direct:produtoByCodigo").routeId("produtoByCodigo")
			.to("sql:select codigo, descricao, valor from produtos where codigo=:#codigo?outputType=SelectOne&outputClass=br.com.study.model.Produto");

		from("file:./pedidos?antInclude=**/*.xml&move=./outbox").routeId("getPedidosXml")
			.choice()
			.when(xpath("/pedido/departamento='TI'")).to(ExchangePattern.InOnly,"seda:processarPedido")
			.otherwise().log(LoggingLevel.WARN, "Pedido ignorado ${header.CamelFileName}").to("file:./pedidos/ignorados");

		from("seda:processarPedido")
			.errorHandler(noErrorHandler())
			.log("${body}")
			.process(exc->{
				Pedido novoPedido = new Pedido();
				novoPedido.setNumPedido(Integer.valueOf(XPathBuilder.xpath("//numero/text()").evaluate(exc, String.class)));
				log.info("novoPedido:{}", novoPedido);
				exc.getIn().setHeader(ProdutoAgregationStrategy.PEDIDO_NOVO, novoPedido);
			})
			.split(xpath("//produto"))
			.parallelProcessing().streaming().executorService(threadPool)
			.log("${body}")
			.setHeader("codigo", xpath("//codigo/text()").convertToString())
			.setHeader("quantidade", xpath("//quantidade/text()").convertToString())
			.enrich("direct:produtoByCodigo", aggregationStrategy)
			.log("${body}")
			.process(exc->{
				Pedido pedido = exc.getIn().getHeader(ProdutoAgregationStrategy.PEDIDO_NOVO, Pedido.class);
				log.info("novoPedido:{}", pedido);
			});
    }
}
