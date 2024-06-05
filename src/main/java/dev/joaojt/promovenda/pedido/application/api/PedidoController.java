package dev.joaojt.promovenda.pedido.application.api;

import java.time.LocalDateTime;
import java.util.List;

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
	public void deletaPedido(Long pedidoId) {
		log.info("[inicia] PedidoController - deletaPedido");
		pedidoService.deletaPedido(pedidoId);
		log.info("[finaliza] PedidoController - deletaPedido");	
	}

	@Override
	public PedidoComItensResponse buscaPedidoComItens(Long pedidoId) {
		log.info("[inicia] PedidoController - buscaPedidoComItens");
		log.info("[pedidoId] {}", pedidoId);
		PedidoComItensResponse pedidoComItensResponse = pedidoService.buscaPedidoComItens(pedidoId);
		log.info("[finaliza] PedidoController - buscaPedidoComItens");
		return pedidoComItensResponse;
	}

	@Override
	public List<PedidoComItensResponse> buscaPedidosComItensPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
		log.info("[inicia] PedidoController - buscaPedidosComItensPorPeriodo");
		List<PedidoComItensResponse> pedidosComItensResponse = pedidoService.buscaPedidosComItensPorPeriodo(dataInicial, dataFinal);
		log.info("[finaliza] PedidoController - buscaPedidosComItensPorPeriodo");
		return pedidosComItensResponse;
	}

}