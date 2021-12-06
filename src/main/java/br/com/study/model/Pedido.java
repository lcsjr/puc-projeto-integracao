package br.com.study.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido implements Serializable {

	private static final long serialVersionUID = 724894274450528069L;

	protected Integer numPedido;
	protected Integer valorTotal;
	protected List<Produto> produtos = new ArrayList<>();
}
