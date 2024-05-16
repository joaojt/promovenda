package dev.joaojt.promovenda.pedido.application.service;

import dev.joaojt.promovenda.pedido.application.api.PedidoNovoRequest;
import dev.joaojt.promovenda.pedido.application.api.PedidoResponse;

public interface PedidoService {

	PedidoResponse inserePedido(PedidoNovoRequest pedidoNovo);

	void deletaPedido(Long idPedido);

}
