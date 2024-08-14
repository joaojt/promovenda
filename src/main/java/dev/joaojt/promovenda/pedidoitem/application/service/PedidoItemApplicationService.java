package dev.joaojt.promovenda.pedidoitem.application.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dev.joaojt.promovenda.handler.APIException;
import dev.joaojt.promovenda.pedido.application.api.PedidoComItensResponse;
import dev.joaojt.promovenda.pedido.application.api.PedidoNovoRequest;
import dev.joaojt.promovenda.pedido.application.repository.PedidoRepository;
import dev.joaojt.promovenda.pedido.application.service.PedidoApplicationService;
import dev.joaojt.promovenda.pedido.domain.Pedido;
import dev.joaojt.promovenda.pedidoitem.application.api.PedidoItemEditaRequest;
import dev.joaojt.promovenda.pedidoitem.application.api.PedidoItemNovoRequest;
import dev.joaojt.promovenda.pedidoitem.application.repository.PedidoItemRepository;
import dev.joaojt.promovenda.pedidoitem.domain.PedidoItem;
import dev.joaojt.promovenda.produto.application.repository.ProdutoRepository;
import dev.joaojt.promovenda.produto.domain.Produto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class PedidoItemApplicationService implements PedidoItemService{
	
	private final PedidoItemRepository pedidoItemRepository;
	private final PedidoApplicationService pedidoApplicationService;
	private final PedidoRepository pedidoRepository;
	private final ProdutoRepository produtoRepository;
	
	@Override
	public PedidoComItensResponse inserePedidoItem(PedidoItemNovoRequest pedidoItemNovo) {
		log.info("[inicia] PedidoItemApplicationService - inserePedidoItem");
		Pedido pedido;
		if (pedidoItemNovo.getPedidoId() != null) {
			pedido = buscaPedidoAberto(pedidoItemNovo.getPedidoId());
		} else {
			pedido = pedidoRepository.buscaPedidoAberto(); //Talvez tratar melhor o retorno que pode ser null (optional)
			if (pedido == null) {
				PedidoNovoRequest pedidoNovo = new PedidoNovoRequest();
			    pedido = new Pedido(pedidoNovo);
			}
		}
		Produto produto = produtoRepository.buscaProdutoPorId(pedidoItemNovo.getProdutoId());
		produto.validaEstoque(pedidoItemNovo);		
		pedido.adicionaPedidoItem(pedidoItemNovo, produto);	
		pedidoRepository.salvaPedido(pedido);
		produto.editaEstoqueSubtrai(pedidoItemNovo);
		produtoRepository.salvaProduto(produto);
		PedidoComItensResponse pedidoComItensResponse = pedidoApplicationService.buscaPedidoComItens(pedido.getId());
		log.info("[finaliza] PedidoItemApplicationService - inserePedidoItem");
		return pedidoComItensResponse;
	}

	@Override
	public void deletaPedidoItem(Long pedidoId, Long produtoId) {
		log.info("[inicia] PedidoItemApplicationService - deletaPedidoItem");
		PedidoItem pedidoItem = pedidoItemRepository.buscaPedidoItem(pedidoId, produtoId);
		buscaPedidoAberto(pedidoItem.getPedido().getId());
		pedidoItem.getProduto().editaEstoqueSoma(pedidoItem.getQtde());
		produtoRepository.salvaProduto(pedidoItem.getProduto());
		pedidoItemRepository.deletaPedidoItem(pedidoItem);
		log.info("[finaliza] PedidoItemApplicationService - deletaPedidoItem");
	}

	@Override
	public PedidoComItensResponse editaPedidoItem(Long pedidoId, Long produtoId, PedidoItemEditaRequest pedidoItemEdita) {
		log.info("[inicia] PedidoItemApplicationService - editaPedidoItem");
		buscaPedidoAberto(pedidoId);
		this.deletaPedidoItem(pedidoId, produtoId);
		PedidoItemNovoRequest pedidoItemNovo = new PedidoItemNovoRequest(pedidoId, produtoId, pedidoItemEdita);
		PedidoComItensResponse pedidoComItensResponse = inserePedidoItem(pedidoItemNovo);
		log.info("[finaliza] PedidoItemApplicationService - editaPedidoItem");
		return pedidoComItensResponse;
	}

	private Pedido buscaPedidoAberto(Long pedidoId) {
		log.info("[inicia] PedidoItemApplicationService - buscaPedidoAberto");
		Pedido pedido = pedidoRepository.buscaPedidoPorId(pedidoId);
		if (!pedido.getAberto()) {
			throw APIException.build(HttpStatus.BAD_REQUEST,"O pedido já está fechado.");
		}
		log.info("[finaliza] PedidoItemApplicationService - buscaPedidoAberto");
		return pedido;
	}
	
}