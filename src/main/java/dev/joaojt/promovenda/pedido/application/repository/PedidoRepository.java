package dev.joaojt.promovenda.pedido.application.repository;

import dev.joaojt.promovenda.pedido.domain.Pedido;

public interface PedidoRepository {

	Pedido salvaPedido(Pedido pedidoNovo);

	void deletaPedido(Pedido pedido);

	Pedido buscaPedidoPorId(Long idPedido);

}