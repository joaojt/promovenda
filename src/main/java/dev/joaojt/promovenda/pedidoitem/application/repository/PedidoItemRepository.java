package dev.joaojt.promovenda.pedidoitem.application.repository;

import java.util.List;

import dev.joaojt.promovenda.pedidoitem.domain.PedidoItem;

public interface PedidoItemRepository {

	List<PedidoItem> buscaPedidoItens(Long pedidoId);

	void deletaPedidoItens(List<PedidoItem> pedidoItens);

	PedidoItem salvaPedidoItem(PedidoItem pedidoItem);

	PedidoItem buscaPedidoItem(Long pedidoId, Long produtoId);

	void deletaPedidoItem(PedidoItem pedidoItem);

	void buscaSeProdutoExisteNaPedidoItem(Long produtoId);

	void buscaSeIdPromocaoExisteNaPedidoItem(Long promocaoId);

}