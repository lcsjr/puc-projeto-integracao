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
		Pedido pedido = originalExchange.getIn().getHeader(PEDIDO_NOVO, Pedido.class);
		try {
			Produto produto = newExchange.getIn().getBody(Produto.class);

			// pedido.getProdutos().add(new ProdutoPedido(produto.getCodigo(), produto.getDescricao(), produto.getValor(),
			// 		originalExchange.getIn().getHeader("quantidade", Integer.class)));

		} catch (Exception e) {
			throw new RuntimeCamelException(e);
		}

		return originalExchange;
	}

}
