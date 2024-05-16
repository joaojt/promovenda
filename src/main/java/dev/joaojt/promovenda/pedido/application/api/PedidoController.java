package dev.joaojt.promovenda.pedido.application.api;

import org.springframework.web.bind.annotation.RestController;

import dev.joaojt.promovenda.pedido.application.service.PedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
public class PedidoController implements PedidoAPI{
	
	private final PedidoService pedidoService;
	
	@Override
	public PedidoResponse inserePedido(PedidoNovoRequest pedidoNovo) {
		log.info("[inicia] PedidoController - inserePedido");
		PedidoResponse pedido = pedidoService.inserePedido(pedidoNovo);
		log.info("[finaliza] PedidoController - inserePedido");
		return pedido;
	}

	@Override
	public void deletaPedido(Long idPedido) {
		log.info("[inicia] PedidoController - deletaPedido");
		pedidoService.deletaPedido(idPedido);
		log.info("[finaliza] PedidoController - deletaPedido");	
	}

}
