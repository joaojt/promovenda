package dev.joaojt.promovenda.pedido.application.service;

import java.time.LocalDateTime;
import java.util.List;

import dev.joaojt.promovenda.pedido.application.api.PedidoComItensResponse;
import dev.joaojt.promovenda.pedido.application.api.PedidoNovoRequest;
import dev.joaojt.promovenda.pedido.application.api.PedidoResponse;

public interface PedidoService {

	PedidoResponse inserePedido(PedidoNovoRequest pedidoNovo);

	void deletaPedido(Long pedidoId);

	PedidoComItensResponse buscaPedidoComItens(Long pedidoId);
	
	List<PedidoComItensResponse> buscaPedidosComItensPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal);

	PedidoComItensResponse fechaPedido();

}