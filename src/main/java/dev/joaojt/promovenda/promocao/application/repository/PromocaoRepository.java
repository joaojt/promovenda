package dev.joaojt.promovenda.promocao.application.repository;

import java.util.List;

import dev.joaojt.promovenda.pedidoitem.application.api.PedidoItemEditaRequest;
import dev.joaojt.promovenda.pedidoitem.domain.PedidoItem;
import dev.joaojt.promovenda.promocao.domain.Promocao;

public interface PromocaoRepository {
	
	Promocao salvaPromocao(Promocao promocaoNova);

	Promocao buscaPromocaoPorId(Long idPromocao);

	List<Promocao> buscaTodasPromocoes();

	void deletaPromocao(Promocao promocao);

	Promocao buscaPromocaoParaEditarPedidoItem(PedidoItemEditaRequest pedidoItemEdita, PedidoItem pedidoItem);
	
}