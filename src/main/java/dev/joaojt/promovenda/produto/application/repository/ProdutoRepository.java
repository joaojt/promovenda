package dev.joaojt.promovenda.produto.application.repository;

import java.util.List;

import dev.joaojt.promovenda.produto.domain.Produto;

public interface ProdutoRepository {
	
	Produto salvaProduto(Produto produtoNovo);

	Produto buscaProdutoPorId(Long idProduto);

	void deletaProduto(Produto produto);

	List<Produto> buscaTodosProdutos();

	void deletaTodosProdutos();
	
}