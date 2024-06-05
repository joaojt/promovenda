package dev.joaojt.promovenda.pedidoitem.infra;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import dev.joaojt.promovenda.handler.APIException;
import dev.joaojt.promovenda.pedidoitem.application.repository.PedidoItemRepository;
import dev.joaojt.promovenda.pedidoitem.domain.PedidoItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
@RequiredArgsConstructor
public class PedidoItemRepositoryDB implements PedidoItemRepository{
	
	private final PedidoItemRepositoryJpa pedidoItemRepositoryJpa;
	
	@Override
	public List<PedidoItem> buscaPedidoItens(Long pedidoId) {
		log.info("[inicia] PedidoItemRepositoryDB - buscaPedidoItens");
		List<PedidoItem> pedidoItens = pedidoItemRepositoryJpa.findAllByPedidoIdOrderByIdAsc(pedidoId);
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
	public PedidoItem buscaPedidoItem(Long pedidoId, Long produtoId) {
		log.info("[inicia] PedidoItemRepositoryDB - buscaPedidoItem");
		PedidoItem pedidoItem = pedidoItemRepositoryJpa.findByPedidoIdAndProdutoId(pedidoId, produtoId)
				.orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Item não encontrado."));
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
	public void buscaSeIdPromocaoExisteNaPedidoItem(Long promocaoId) {
		log.info("[inicia] PedidoItemRepositoryDB - buscaSeIdPromocaoExisteNaPedidoItem");
		if (pedidoItemRepositoryJpa.findFirstByPromocaoId(promocaoId).isPresent()) {
			log.info("[finaliza] PedidoItemRepositoryDB - buscaSeIdPromocaoExisteNaPedidoItem");
			throw APIException.build(HttpStatus.BAD_REQUEST,
					"Existe(m) item(s) de pedido(s) relacionado(s) à esta promoção, por isso não é possível excluí-la ou editá-la.");
		};
	}

	@Override
	public void buscaSeProdutoExisteNaPedidoItem(Long produtoId) {
		log.info("[inicia] PedidoItemRepositoryDB - buscaSeProdutoExisteNaPedidoItem");
		if (pedidoItemRepositoryJpa.findFirstByProdutoId(produtoId).isPresent()) {
			log.info("[finaliza] PedidoItemRepositoryDB - buscaSeProdutoExisteNaPedidoItem");
			throw APIException.build(HttpStatus.BAD_REQUEST,
					"Existe(m) item(s) de pedido(s) relacionado(s) à este produto, por isso não é possível excluí-lo.");
		}
	}
	
}