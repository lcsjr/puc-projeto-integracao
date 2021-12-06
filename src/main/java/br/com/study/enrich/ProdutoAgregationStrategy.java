package br.com.study.enrich;

import br.com.study.model.Produto;
import br.com.study.model.Pedido;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.RuntimeCamelException;
import org.springframework.stereotype.Component;

@Component
public class ProdutoAgregationStrategy implements AggregationStrategy {

	public static final String PEDIDO_NOVO = "pedidoNovo";

	@Override
	public Exchange aggregate(Exchange originalExchange, Exchange newExchange) {
		try {
			Pedido pedido = originalExchange.getIn().getHeader(PEDIDO_NOVO, Pedido.class);
			Produto produto = newExchange.getIn().getBody(Produto.class);
			pedido.getProdutos().add(produto);
		} catch (Exception e) {
			throw new RuntimeCamelException(e);
		}

		return originalExchange;
	}

}
