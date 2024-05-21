package dev.joaojt.promovenda.pedidoitem.application.repository;

import java.util.List;
import java.util.Optional;

import dev.joaojt.promovenda.pedidoitem.domain.PedidoItem;

public interface PedidoItemRepository {

	List<PedidoItem> buscaPedidoItens(Long idPedido);

	void deletaPedidoItens(List<PedidoItem> pedidoItens);

	PedidoItem salvaPedidoItem(PedidoItem pedidoItem);

	Boolean buscaSePedidoItemJaExiste(Long idPedido, Long idProduto);

	Optional<PedidoItem> buscaPedidoItemExistente(Long idPedido, Long idProduto);

	void deletaPedidoItem(PedidoItem pedidoItem);

	void existePedidoItemPorIdPromocao(Long idPromocao);

}