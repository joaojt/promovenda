package dev.joaojt.promovenda.pedidoitem.infra;

import java.util.List;
import java.util.Optional;

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
	public void existePedidoItemPorIdPromocao(Long idPromocao) {
		log.info("[inicia] PedidoItemRepositoryDB - existePedidoItemPorIdPromocao");
		pedidoItemRepositoryJpa.findFirstByIdPromocao(idPromocao).ifPresent(pedidoItem -> {
			log.info("[finaliza] PedidoItemRepositoryDB - existePedidoItemPorIdPromocao");
			throw APIException.build(HttpStatus.BAD_REQUEST,
					"Existe(m) item(s) de pedido(s) relacionado(s) à esta promoção, por isso não é possível excluí-la ou editá-la.");
		});
	}
	
}