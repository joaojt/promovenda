package dev.joaojt.promovenda.pedidoitem.application.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dev.joaojt.promovenda.handler.APIException;
import dev.joaojt.promovenda.pedido.application.api.PedidoComItensResponse;
import dev.joaojt.promovenda.pedido.application.repository.PedidoRepository;
import dev.joaojt.promovenda.pedido.application.service.PedidoApplicationService;
import dev.joaojt.promovenda.pedidoitem.application.api.PedidoItemEditaRequest;
import dev.joaojt.promovenda.pedidoitem.application.api.PedidoItemNovoRequest;
import dev.joaojt.promovenda.pedidoitem.application.repository.PedidoItemRepository;
import dev.joaojt.promovenda.pedidoitem.domain.PedidoItem;
import dev.joaojt.promovenda.produto.application.repository.ProdutoRepository;
import dev.joaojt.promovenda.produto.domain.Produto;
import dev.joaojt.promovenda.promocao.application.repository.PromocaoRepository;
import dev.joaojt.promovenda.promocao.domain.Promocao;
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
	private final PromocaoRepository promocaoRepository;
	
	@Override
	public PedidoComItensResponse inserePedidoItem(PedidoItemNovoRequest pedidoItemNovo) {
		log.info("[inicia] PedidoItemApplicationService - inserePedidoItem");	
		pedidoRepository.buscaPedidoPorId(pedidoItemNovo.getIdPedido());
		Produto produto = produtoRepository.buscaProdutoPorId(pedidoItemNovo.getIdProduto());
		Promocao promocao = produto.getIdPromocao() != null ? promocaoRepository.buscaPromocaoPorId(produto.getIdPromocao()) : null;
		produto.validaEstoqueInserePedidoItem(pedidoItemNovo);	
		PedidoItem pedidoItem = pedidoItemRepository
				.buscaPedidoItemExistente(pedidoItemNovo.getIdPedido(), pedidoItemNovo.getIdProduto()).get();
		pedidoItemRepository.incrementaESalvaPedidoItemExistente(pedidoItemNovo, promocao, pedidoItem, produto);
		produto.editaEstoqueSubtrai(pedidoItemNovo);
		produtoRepository.salvaProduto(produto);		
		PedidoComItensResponse pedidoComItensResponse = pedidoApplicationService.buscaPedidoComItens(pedidoItemNovo.getIdPedido());
		log.info("[finaliza] PedidoItemApplicationService - inserePedidoItem");
		return pedidoComItensResponse;
	}

	@Override
	public void deletaPedidoItem(Long idPedido, Long idProduto) {
		log.info("[inicia] PedidoItemApplicationService - deletaPedidoItem");
		PedidoItem pedidoItem = pedidoItemRepository.buscaPedidoItemExistente(idPedido, idProduto)
				.orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Item não encontrado."));
		Integer qtdePedidoItem = pedidoItem.getQtde(); //Pego a qtde aqui
		pedidoItemRepository.deletaPedidoItem(pedidoItem); //Deleto pedidoItem aqui
		Produto produto = produtoRepository.buscaProdutoPorId(idProduto);
		produto.editaEstoqueSoma(qtdePedidoItem); //Aqui o pedidoItem.getQtde() ainda seria o mesmo?							
		produtoRepository.salvaProduto(produto);
		log.info("[finaliza] PedidoItemApplicationService - deletaPedidoItem");
	}

	@Override
	public PedidoComItensResponse editaPedidoItem(Long idPedido, Long idProduto, PedidoItemEditaRequest pedidoItemEdita) {
		log.info("[inicia] PedidoItemApplicationService - editaPedidoItem");
		pedidoRepository.buscaPedidoPorId(idPedido);
		Produto produto = produtoRepository.buscaProdutoPorId(idProduto);
		PedidoItem pedidoItem = pedidoItemRepository.buscaPedidoItemExistente(idPedido, idProduto)
				.orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Item não encontrado."));		
		Promocao promocao = promocaoRepository.buscaPromocaoParaEditarPedidoItem(pedidoItemEdita, pedidoItem);
		pedidoItem.editaPedidoItemExistente(pedidoItemEdita, promocao);
		pedidoItemRepository.salvaPedidoItem(pedidoItem);
		if (pedidoItemEdita.getQtde() != null) {
			produto.validaEstoqueEditaPedidoItem(pedidoItemEdita, pedidoItem);
			produtoRepository.salvaProduto(produto);				
		}
		PedidoComItensResponse pedidoComItensResponse = pedidoApplicationService.buscaPedidoComItens(idPedido);
		log.info("[finaliza] PedidoItemApplicationService - editaPedidoItem");
		return pedidoComItensResponse;
	}

}