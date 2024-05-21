package dev.joaojt.promovenda.pedidoitem.application.service;

import dev.joaojt.promovenda.pedido.application.api.PedidoComItensResponse;
import dev.joaojt.promovenda.pedidoitem.application.api.PedidoItemNovoRequest;

public interface PedidoItemService {

	PedidoComItensResponse inserePedidoItem(PedidoItemNovoRequest pedidoItemNovo);

	void deletaPedidoItem(Long idPedido, Long idProduto);

}