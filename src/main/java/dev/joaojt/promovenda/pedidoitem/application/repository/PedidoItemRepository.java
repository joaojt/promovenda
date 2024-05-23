package dev.joaojt.promovenda.pedidoitem.application.repository;

import java.util.List;
import java.util.Optional;

import dev.joaojt.promovenda.pedidoitem.application.api.PedidoItemNovoRequest;
import dev.joaojt.promovenda.pedidoitem.domain.PedidoItem;
import dev.joaojt.promovenda.produto.domain.Produto;
import dev.joaojt.promovenda.promocao.domain.Promocao;

public interface PedidoItemRepository {

	List<PedidoItem> buscaPedidoItens(Long idPedido);

	void deletaPedidoItens(List<PedidoItem> pedidoItens);

	PedidoItem salvaPedidoItem(PedidoItem pedidoItem);

	Boolean buscaSePedidoItemJaExiste(Long idPedido, Long idProduto);

	Optional<PedidoItem> buscaPedidoItemExistente(Long idPedido, Long idProduto);

	void deletaPedidoItem(PedidoItem pedidoItem);

	void buscaSeProdutoExisteNaPedidoItem(Long idProduto);

	void buscaSeIdPromocaoExisteNaPedidoItem(Long idPromocao);

	void incrementaESalvaPedidoItemExistente(PedidoItemNovoRequest pedidoItemNovo, Promocao promocao,
			PedidoItem pedidoItem, Produto produto);

}