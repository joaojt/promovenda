package dev.joaojt.promovenda.pedidoitem.application.api;

import java.util.List;
import java.util.stream.Collectors;

import dev.joaojt.promovenda.pedidoitem.domain.PedidoItem;
import lombok.Getter;

@Getter
public class PedidoItemResponse {
	
	private final Long id;
	private final Long idPedido;
	private final Long idProduto;
	private final Long idPromocao;
	private final int qtde;
	private final double vlrUnitario;
	private final double vlrTotal;
	
	public PedidoItemResponse (PedidoItem pedidoItem) {
		this.id = pedidoItem.getId();
		this.idPedido = pedidoItem.getIdPedido();
		this.idProduto = pedidoItem.getIdProduto();
		this.idPromocao = pedidoItem.getIdPromocao();
		this.qtde = pedidoItem.getQtde();
		this.vlrUnitario = pedidoItem.getVlrUnitario();
		this.vlrTotal = pedidoItem.getVlrTotal();
	}
	
	public static List<PedidoItemResponse> converter(List<PedidoItem> pedidoItens) {
		return pedidoItens.stream()
				.map(PedidoItemResponse::new)
				.collect(Collectors.toList());
	}

}