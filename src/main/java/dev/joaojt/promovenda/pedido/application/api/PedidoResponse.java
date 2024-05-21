package dev.joaojt.promovenda.pedido.application.api;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import dev.joaojt.promovenda.pedido.domain.Pedido;
import lombok.Getter;

@Getter
public class PedidoResponse {
	
	private final Long id;
	private final String cliente;
    private final String data;

	public PedidoResponse(Pedido pedido) {
		this.id = pedido.getId();
		this.cliente = pedido.getCliente();
        DateTimeFormatter formatoDesejado = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.data = pedido.getData().format(formatoDesejado);		
	}

	public static List<PedidoResponse> converter(List<Pedido> pedidos) {
		return pedidos.stream()
				.map(PedidoResponse::new)
				.collect(Collectors.toList());
	}

}