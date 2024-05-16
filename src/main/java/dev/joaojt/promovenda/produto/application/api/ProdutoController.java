package dev.joaojt.promovenda.produto.application.api;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import dev.joaojt.promovenda.produto.application.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
public class ProdutoController implements ProdutoAPI{
	
	private final ProdutoService produtoService;

	@Override
	public ProdutoResponse insereProduto(ProdutoNovoRequest produtoNovo) {
		log.info("[inicia] ProdutoController - insereProduto");
		ProdutoResponse produto = produtoService.insereProduto(produtoNovo);
		log.info("[finaliza] ProdutoController - insereProduto");
		return produto;
	}
    
	@Override
	public void deletaProduto(Long idProduto) {
		log.info("[inicia] ProdutoController - deletaProduto");
		produtoService.deletaProduto(idProduto);
		log.info("[finaliza] ProdutoController - deletaProduto");
	}
	
	@Override
	public void deletaTodosProdutos() {
		log.info("[inicia] ProdutoController - deletaTodosProdutos");
		produtoService.deletaTodosProdutos();
		log.info("[finaliza] ProdutoController - deletaTodosProdutos");
	}	

	@Override
	public ProdutoResponse buscaProdutoPorId(Long idProduto) {
		log.info("[inicia] ProdutoController - buscaProdutoPorId");
		log.info("[idProduto] {}", idProduto);
		ProdutoResponse produtoResponse = produtoService.buscaProdutoPorId(idProduto);
		log.info("[finaliza] ProdutoController - buscaProdutoPorId");
		return produtoResponse;
	}

	@Override
	public void editaProduto(Long idProduto, ProdutoEditaRequest produtoEdita) {
		log.info("[inicia] ProdutoController - editaProduto");
		produtoService.editaProduto(idProduto, produtoEdita);
		log.info("[finaliza] ProdutoController - editaProduto");			
	}

	@Override
	public List<ProdutoResponse> buscaTodosProdutos() {
		log.info("[inicia] ProdutoController - buscaTodosProdutos");			
		List<ProdutoResponse> produtos = produtoService.buscaTodosProdutos();
		log.info("[finaliza] ProdutoController - buscaTodosProdutos");			
		return produtos;
	}

}