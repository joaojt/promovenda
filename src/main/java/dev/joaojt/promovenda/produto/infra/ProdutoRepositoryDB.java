package dev.joaojt.promovenda.produto.infra;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import dev.joaojt.promovenda.handler.APIException;
import dev.joaojt.promovenda.produto.application.repository.ProdutoRepository;
import dev.joaojt.promovenda.produto.domain.Produto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
@RequiredArgsConstructor
public class ProdutoRepositoryDB implements ProdutoRepository{
	
	private final ProdutoRepositoryJpa produtoRepositoryJpa;
	
	@Override
	public Produto salvaProduto(Produto produto) {
		log.info("[inicia] ProdutoRepositoryDB - salvaProduto");
		Produto produtoSalvo = produtoRepositoryJpa.save(produto);
		log.info("[finaliza] ProdutoRepositoryDB - salvaProduto");
		return produtoSalvo; 
	}

	@Override
	public Produto buscaProdutoPorId(Long idProduto) {
		log.info("[inicia] ProdutoRepositoryDB - buscaProdutoPorId");
		Produto produto = produtoRepositoryJpa.findById(idProduto)
				.orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Produto não encontrado."));
		log.info("[finaliza] ProdutoRepositoryDB - buscaProdutoPorId");
		return produto;
	}
	
	@Override
	public void deletaProduto(Produto produto) {
		log.info("[inicia] ProdutoRepositoryDB - deletaProduto");
		produtoRepositoryJpa.delete(produto);
		log.info("[finaliza] ProdutoRepositoryDB - deletaProduto");
	}

	@Override
	public List<Produto> buscaTodosProdutos() {
		log.info("[inicia] ProdutoRepositoryDB - buscaTodosProdutos");
		List<Produto> produtos = produtoRepositoryJpa.findAll();
		log.info("[finaliza] ProdutoRepositoryDB - buscaTodosProdutos");
		return produtos;
	}

	@Override
	public void deletaTodosProdutos() {
		log.info("[inicia] ProdutoRepositoryDB - deletaTodosProdutos");
		produtoRepositoryJpa.deleteAll();
		log.info("[finaliza] ProdutoRepositoryDB - deletaTodosProdutos");
	}
	
}