package dev.joaojt.promovenda.pedido.application.api;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import dev.joaojt.promovenda.pedido.domain.Pedido;
import dev.joaojt.promovenda.pedidoitem.application.api.PedidoItemResponse;
import dev.joaojt.promovenda.pedidoitem.domain.PedidoItem;
import lombok.Getter;

@Getter
public class PedidoComItensResponse {

	private Long id;
	private String cliente;
	private String data;	
	private Boolean aberto;
	private Double vlrTotalPedido;
    private List<PedidoItemResponse> itens; 
    
    public PedidoComItensResponse(Pedido pedido) {
		this.id = pedido.getId();
		this.cliente = pedido.getCliente();
		DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");		
		this.data = pedido.getData().format(dataFormatada);
		this.aberto = pedido.getAberto();
		this.vlrTotalPedido = calculaVlrTotalPedido(pedido.getPedidoItem());
		this.itens = pedido.getPedidoItem().stream()
				.map(PedidoItemResponse::new)
				.collect(Collectors.toList());
	}

	private Double calculaVlrTotalPedido(List<PedidoItem> itens) {
		return itens.stream()
				.mapToDouble(PedidoItem::getVlrTotal)
				.sum();
	}    
    
}