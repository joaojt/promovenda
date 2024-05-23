package dev.joaojt.promovenda.produto.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.joaojt.promovenda.pedidoitem.application.repository.PedidoItemRepository;
import dev.joaojt.promovenda.produto.application.api.ProdutoEditaRequest;
import dev.joaojt.promovenda.produto.application.api.ProdutoNovoRequest;
import dev.joaojt.promovenda.produto.application.api.ProdutoResponse;
import dev.joaojt.promovenda.produto.application.repository.ProdutoRepository;
import dev.joaojt.promovenda.produto.domain.Produto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProdutoApplicationService implements ProdutoService{
	
	private final ProdutoRepository produtoRepository;
	private final PedidoItemRepository pedidoItemRepository;
	
	@Override
	public ProdutoResponse insereProduto(ProdutoNovoRequest produtoNovo) {
		log.info("[inicia] ProdutoApplicationService - insereProduto");
		Produto produto = produtoRepository.salvaProduto(new Produto(produtoNovo));
		log.info("[finaliza] ProdutoApplicationService - insereProduto");
		return new ProdutoResponse(produto);
	}

	@Override
	public ProdutoResponse buscaProdutoPorId(Long idProduto) {
		log.info("[inicia] ProdutoApplicationService - buscaProdutoPorId");
		Produto produto = produtoRepository.buscaProdutoPorId(idProduto);
		log.info("[finaliza] ProdutoApplicationService - buscaProdutoPorId");
		return new ProdutoResponse(produto);
	}

	@Override
	public void deletaProduto(Long idProduto) {
		log.info("[inicia] ProdutoApplicationService - deletaProduto");
		Produto produto = produtoRepository.buscaProdutoPorId(idProduto);
		pedidoItemRepository.buscaSeProdutoExisteNaPedidoItem(idProduto);
		produtoRepository.deletaProduto(produto);
		log.info("[finaliza] ProdutoApplicationService - deletaProduto");
	}
	
	@Override
	public void deletaTodosProdutos() {
		log.info("[finaliza] ProdutoApplicationService - deletaTodosProdutos");
		produtoRepository.deletaTodosProdutos();
		log.info("[finaliza] ProdutoApplicationService - deletaTodosProdutos");
	}	

	@Override
	public void editaProduto(Long idProduto, ProdutoEditaRequest produtoEdita) {
		log.info("[inicia] ProdutoApplicationService - editaProduto");
		Produto produto = produtoRepository.buscaProdutoPorId(idProduto);
		produto.editaProduto(produtoEdita);
		produtoRepository.salvaProduto(produto);
		log.info("[finaliza] ProdutoApplicationService - editaProduto");
	}
	
	@Override
	public List<ProdutoResponse> buscaTodosProdutos() {
		log.info("[inicia] ProdutoApplicationService - buscaTodosProdutos");
		List<Produto> produtos = produtoRepository.buscaTodosProdutos();
		log.info("[finaliza] ProdutoApplicationService - buscaTodosProdutos");
		return ProdutoResponse.converter(produtos);
	}	
	
}