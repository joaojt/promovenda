package dev.joaojt.promovenda.pedido.application.service;

import org.springframework.stereotype.Service;

import dev.joaojt.promovenda.pedido.application.api.PedidoNovoRequest;
import dev.joaojt.promovenda.pedido.application.api.PedidoResponse;
import dev.joaojt.promovenda.pedido.application.repository.PedidoRepository;
import dev.joaojt.promovenda.pedido.domain.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class PedidoApplicationService implements PedidoService{
	
	private final PedidoRepository pedidoRepository;
	
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
		//deletar primeiro os itens do pedido
		pedidoRepository.deletaPedido(pedido);
		log.info("[finaliza] PedidoApplicationService - deletaPedido");
		
	}

}
