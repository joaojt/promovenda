package dev.joaojt.promovenda.produto.application.service;

import java.util.List;

import dev.joaojt.promovenda.produto.application.api.ProdutoEditaRequest;
import dev.joaojt.promovenda.produto.application.api.ProdutoNovoRequest;
import dev.joaojt.promovenda.produto.application.api.ProdutoResponse;

public interface ProdutoService {

	ProdutoResponse insereProduto(ProdutoNovoRequest produtoNovo);

	ProdutoResponse buscaProdutoPorId(Long idProduto);

	void deletaProduto(Long idProduto);

	void editaProduto(Long idProduto, ProdutoEditaRequest produtoEdita);

	List<ProdutoResponse> buscaTodosProdutos();

	void deletaTodosProdutos();
}
