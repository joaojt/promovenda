package dev.joaojt.promovenda.pedidoitem.application.repository;

import java.util.List;

import dev.joaojt.promovenda.pedidoitem.domain.PedidoItem;

public interface PedidoItemRepository {

	List<PedidoItem> buscaItensPedido(Long idPedido);

	void deletaItensPedido(List<PedidoItem> itensPedido);

}
