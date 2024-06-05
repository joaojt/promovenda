package dev.joaojt.promovenda.pedidoitem.application.api;

import java.util.List;
import java.util.stream.Collectors;

import dev.joaojt.promovenda.pedidoitem.domain.PedidoItem;
import lombok.Getter;

@Getter
public class PedidoItemResponse {
	
	private Long id;
	private Long pedidoId;
	private Long produtoId;
	private Long promocaoId;
	private Integer qtde;
	private Double vlrUnitario;
	private Double vlrTotal;
	private String descPromocao;
	
	public PedidoItemResponse (PedidoItem pedidoItem) {
		this.id = pedidoItem.getId();
		this.pedidoId = pedidoItem.getPedido().getId();
		this.produtoId = pedidoItem.getProduto().getId();
		this.promocaoId = pedidoItem.getPromocao().getId();		
		this.qtde = pedidoItem.getQtde();
		this.vlrUnitario = pedidoItem.getVlrUnitario();
		this.vlrTotal = pedidoItem.getVlrTotal();
		this.descPromocao = pedidoItem.getPromocao() != null ? pedidoItem.getPromocao().getDescPromocao() : null;
	}
	
	public static List<PedidoItemResponse> converter(List<PedidoItem> pedidoItens) {
		return pedidoItens.stream()
				.map(PedidoItemResponse::new)
				.collect(Collectors.toList());
	}

}