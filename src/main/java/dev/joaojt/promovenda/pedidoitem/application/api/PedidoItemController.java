package dev.joaojt.promovenda.pedidoitem.application.api;

import org.springframework.web.bind.annotation.RestController;

import dev.joaojt.promovenda.pedido.application.api.PedidoComItensResponse;
import dev.joaojt.promovenda.pedidoitem.application.service.PedidoItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
public class PedidoItemController implements PedidoItemAPI{
	
	private final PedidoItemService pedidoItemService;
	
	@Override
	public PedidoComItensResponse inserePedidoItem(PedidoItemNovoRequest pedidoItemNovo) {
		log.info("[inicia] PedidoItemController - inserePedidoItem");
		PedidoComItensResponse pedidoComItens = pedidoItemService.inserePedidoItem(pedidoItemNovo);
		log.info("[finaliza] PedidoItemController - inserePedidoItem");
		return pedidoComItens;
	}

	@Override
	public void deletaPedidoItem(Long idPedido, Long idProduto) {
		log.info("[inicia] PedidoItemController - deletaPedidoItem");
		pedidoItemService.deletaPedidoItem(idPedido, idProduto);
		log.info("[finaliza] PedidoItemController - deletaPedidoItem");
	}

}