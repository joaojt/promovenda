package dev.joaojt.promovenda.pedidoitem.application.service;

import dev.joaojt.promovenda.pedido.application.api.PedidoComItensResponse;
import dev.joaojt.promovenda.pedidoitem.application.api.PedidoItemEditaRequest;
import dev.joaojt.promovenda.pedidoitem.application.api.PedidoItemNovoRequest;

public interface PedidoItemService {

	PedidoComItensResponse inserePedidoItem(PedidoItemNovoRequest pedidoItemNovo);

	void deletaPedidoItem(Long pedidoId, Long produtoId);

	PedidoComItensResponse editaPedidoItem(Long pedidoId, Long produtoId, PedidoItemEditaRequest pedidoItemEdita);

}