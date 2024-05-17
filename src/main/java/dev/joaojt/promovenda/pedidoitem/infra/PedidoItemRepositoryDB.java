package dev.joaojt.promovenda.pedidoitem.infra;

import java.util.List;

import org.springframework.stereotype.Repository;

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
	public List<PedidoItem> buscaItensPedido(Long idPedido) {
		log.info("[inicia] PedidoItemRepositoryDB - buscaItensPedido");
		List<PedidoItem> itensPedido = pedidoItemRepositoryJpa.findAllByIdPedidoOrderByIdAsc(idPedido);
		log.info("[finaliza] PedidoItemRepositoryDB - buscaItensPedido");
		return itensPedido;
	}

	@Override
	public void deletaItensPedido(List<PedidoItem> itensPedido) {
		log.info("[inicia] PedidoItemRepositoryDB - deletaItensPedido");
		pedidoItemRepositoryJpa.deleteAll(itensPedido);
		log.info("[finaliza] PedidoItemRepositoryDB - deletaItensPedido");
		
	}
	
}
