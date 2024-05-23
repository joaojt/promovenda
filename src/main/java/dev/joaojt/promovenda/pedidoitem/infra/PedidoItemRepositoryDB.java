package dev.joaojt.promovenda.pedidoitem.infra;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import dev.joaojt.promovenda.handler.APIException;
import dev.joaojt.promovenda.pedidoitem.application.api.PedidoItemNovoRequest;
import dev.joaojt.promovenda.pedidoitem.application.repository.PedidoItemRepository;
import dev.joaojt.promovenda.pedidoitem.domain.PedidoItem;
import dev.joaojt.promovenda.produto.domain.Produto;
import dev.joaojt.promovenda.promocao.domain.Promocao;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
@RequiredArgsConstructor
public class PedidoItemRepositoryDB implements PedidoItemRepository{
	
	private final PedidoItemRepositoryJpa pedidoItemRepositoryJpa;
	
	@Override
	public List<PedidoItem> buscaPedidoItens(Long idPedido) {
		log.info("[inicia] PedidoItemRepositoryDB - buscaPedidoItens");
		List<PedidoItem> pedidoItens = pedidoItemRepositoryJpa.findAllByIdPedidoOrderByIdAsc(idPedido);
		log.info("[finaliza] PedidoItemRepositoryDB - buscaPedidoItens");
		return pedidoItens;
	}

	@Override
	public void deletaPedidoItens(List<PedidoItem> pedidoItens) {
		log.info("[inicia] PedidoItemRepositoryDB - deletaPedidoItens");
		pedidoItemRepositoryJpa.deleteAll(pedidoItens);
		log.info("[finaliza] PedidoItemRepositoryDB - deletaPedidoItens");
	}

	@Override
	public PedidoItem salvaPedidoItem(PedidoItem pedidoItem) {
		log.info("[inicia] PedidoItemRepositoryDB - salvaPedidoItem");
		PedidoItem itemPedido = pedidoItemRepositoryJpa.save(pedidoItem);
		log.info("[finaliza] PedidoItemRepositoryDB - salvaPedidoItem");
		return itemPedido;
	}

	@Override
	public Boolean buscaSePedidoItemJaExiste(Long idPedido, Long idProduto) {
		log.info("[inicia] PedidoItemRepositoryDB - buscaSePedidoItemJaExiste");
		Boolean pedidoItemJaExiste = pedidoItemRepositoryJpa.findByIdPedidoAndIdProduto(idPedido, idProduto).isPresent();
		log.info("[finaliza] PedidoItemRepositoryDB - buscaSePedidoItemJaExiste");
		return pedidoItemJaExiste;
	}

	@Override
	public Optional<PedidoItem> buscaPedidoItemExistente(Long idPedido, Long idProduto) {
		log.info("[inicia] PedidoItemRepositoryDB - buscaPedidoItem");
		Optional<PedidoItem> pedidoItem = pedidoItemRepositoryJpa.findByIdPedidoAndIdProduto(idPedido, idProduto);
		log.info("[finaliza] PedidoItemRepositoryDB - buscaPedidoItem");
		return pedidoItem;
	}

	@Override
	public void deletaPedidoItem(PedidoItem pedidoItem) {
		log.info("[inicia] PedidoItemRepositoryDB - deletaPedidoItem");
		pedidoItemRepositoryJpa.delete(pedidoItem);
		log.info("[finaliza] PedidoItemRepositoryDB - deletaPedidoItem");
	}

	@Override
	public void buscaSeIdPromocaoExisteNaPedidoItem(Long idPromocao) {
		log.info("[inicia] PedidoItemRepositoryDB - buscaSeIdPromocaoExisteNaPedidoItem");
		if (pedidoItemRepositoryJpa.findFirstByIdPromocao(idPromocao).isPresent()) {
			log.info("[finaliza] PedidoItemRepositoryDB - buscaSeIdPromocaoExisteNaPedidoItem");
			throw APIException.build(HttpStatus.BAD_REQUEST,
					"Existe(m) item(s) de pedido(s) relacionado(s) à esta promoção, por isso não é possível excluí-la ou editá-la.");
		};
	}

	@Override
	public void buscaSeProdutoExisteNaPedidoItem(Long idProduto) {
		log.info("[inicia] PedidoItemRepositoryDB - buscaSeProdutoExisteNaPedidoItem");
		if (pedidoItemRepositoryJpa.findFirstByIdProduto(idProduto).isPresent()) {
			log.info("[finaliza] PedidoItemRepositoryDB - buscaSeProdutoExisteNaPedidoItem");
			throw APIException.build(HttpStatus.BAD_REQUEST,
					"Existe(m) item(s) de pedido(s) relacionado(s) à este produto, por isso não é possível excluí-lo.");
		}
	}

	@Override
	public void incrementaESalvaPedidoItemExistente(PedidoItemNovoRequest pedidoItemNovo, Promocao promocao,
			PedidoItem pedidoItem, Produto produto) {
		log.info("[inicia] PedidoItemRepositoryDB - incrementaESalvaPedidoItemExistente");
		if (pedidoItem != null) {
			pedidoItem.incrementaPedidoItemExistente(pedidoItemNovo, promocao);
			salvaPedidoItem(pedidoItem);
		} else {
			salvaPedidoItem(new PedidoItem(pedidoItemNovo, produto, promocao));
		}
		log.info("[finaliza] PedidoItemRepositoryDB - incrementaESalvaPedidoItemExistente");
	}
	
}