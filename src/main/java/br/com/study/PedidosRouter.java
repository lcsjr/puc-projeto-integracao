package br.com.study;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PedidosRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

    	from("file:./pedidos?charset=utf-8")
			.routeId("enrich")
			.log("Hello World")
    	.to("file:./pedidos/outbox");

    }
}
