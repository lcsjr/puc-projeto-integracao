package br.com.study;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.study.model.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PedidosRouter extends RouteBuilder {
	@Autowired
	private final ProdutoRepository produtoRepo;

	public PedidosRouter(ProdutoRepository produtoRepo) {
		this.produtoRepo = produtoRepo;
	}

	@Override
    public void configure() throws Exception {

    	// from("file:./pedidos")
		// 	.routeId("enrich")
		// 	// .convertBodyTo(String.class)
		// 	// .log("Hello World")
		// 	.log("${body}")
		// 	// .split(xpath("//produto"))
		// 	// .log(body())
		// 	// .process((exc)->{
		// 	// 	var prod = exc.getBody(Produto.class);
		// 	// 	produtoRepo.findByCodigo(prod.getCodigo());

		// 	// 	log.info("prod:{}", prod);

		// 	// 	// Pedido pedido = new Pedido();
		// 	// 	// pedido.setNumPedido(Integer.valueOf(XPathBuilder.xpath("//numero/text()").evaluate(exc,String.class)));
		// 	// 	// exc.getIn().setHeader(ProdutoNovoItemAgregationStrategy.PEDIDO_NOVO, pedido);
		// 	// })
		// 	// .log(body())
		// 	// // .parallelProcessing().streaming().executorService(threadPool)
		// 	// .setHeader("codigo", xpath("//codigo/text()").convertToString())
		// 	// .setHeader("quantidade", xpath("//quantidade/text()").convertToString())
		// 	// .enrich("direct:produto", "produtoAgregationStrategy")
			// .to("file:./pedidos/outbox");

    	from("file:./pedidos")
			.routeId("enrich")
			.log("${body}")
			.to("file:./pedidos/outbox");
    }
}
