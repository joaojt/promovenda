package dev.joaojt.promovenda.pedido.application.service;

import java.time.LocalDateTime;
import java.util.List;

import dev.joaojt.promovenda.pedido.application.api.PedidoComItensResponse;
import dev.joaojt.promovenda.pedido.application.api.PedidoNovoRequest;
import dev.joaojt.promovenda.pedido.application.api.PedidoResponse;

public interface PedidoService {

	PedidoResponse inserePedido(PedidoNovoRequest pedidoNovo);

	void deletaPedido(Long idPedido);

	PedidoComItensResponse buscaPedidoComItens(Long idPedido);
	
	List<PedidoComItensResponse> buscaPedidosComItensPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal);

}