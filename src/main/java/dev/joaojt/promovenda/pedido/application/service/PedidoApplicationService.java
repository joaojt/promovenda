package dev.joaojt.promovenda.pedido.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.joaojt.promovenda.pedido.application.api.PedidoComItensResponse;
import dev.joaojt.promovenda.pedido.application.api.PedidoNovoRequest;
import dev.joaojt.promovenda.pedido.application.api.PedidoResponse;
import dev.joaojt.promovenda.pedido.application.repository.PedidoRepository;
import dev.joaojt.promovenda.pedido.domain.Pedido;
import dev.joaojt.promovenda.pedidoitem.application.repository.PedidoItemRepository;
import dev.joaojt.promovenda.pedidoitem.domain.PedidoItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class PedidoApplicationService implements PedidoService{
	
	private final PedidoRepository pedidoRepository;
	private final PedidoItemRepository pedidoItemRepository;
	
	@Override
	public PedidoResponse inserePedido(PedidoNovoRequest pedidoNovo) {
		log.info("[inicia] PedidoApplicationService - inserePedido");
		Pedido pedido = pedidoRepository.salvaPedido(new Pedido(pedidoNovo));
		log.info("[finaliza] PedidoApplicationService - inserePedido");
		return new PedidoResponse(pedido);
	}

	@Override
	public void deletaPedido(Long idPedido) {
		log.info("[inicia] PedidoApplicationService - deletaPedido");
		Pedido pedido = pedidoRepository.buscaPedidoPorId(idPedido);
		List<PedidoItem> itensPedido = pedidoItemRepository.buscaItensPedido(idPedido);
	    Optional.ofNullable(itensPedido)
	    		.filter(itens -> !itens.isEmpty())
	    		.ifPresent(pedidoItemRepository::deletaItensPedido);
		pedidoRepository.deletaPedido(pedido);
		log.info("[finaliza] PedidoApplicationService - deletaPedido");	
	}

	@Override
	public PedidoComItensResponse buscaPedidoComItens(Long idPedido) {
		log.info("[inicia] PedidoApplicationService - buscaPedidoComItens");
		Pedido pedido = pedidoRepository.buscaPedidoPorId(idPedido);
		List<PedidoItem> itensPedido = pedidoItemRepository.buscaItensPedido(idPedido);	
		PedidoComItensResponse pedidoComItensResponse = new PedidoComItensResponse(pedido, itensPedido);
		log.info("[finaliza] PedidoApplicationService - buscaPedidoComItens");	
		return pedidoComItensResponse;
	}

}
