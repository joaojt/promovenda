package dev.joaojt.promovenda.produto.application.service;

import java.util.List;

import dev.joaojt.promovenda.produto.application.api.ProdutoEditaRequest;
import dev.joaojt.promovenda.produto.application.api.ProdutoNovoRequest;
import dev.joaojt.promovenda.produto.application.api.ProdutoResponse;

public interface ProdutoService {

	ProdutoResponse insereProduto(ProdutoNovoRequest produtoNovo);

	ProdutoResponse buscaProdutoPorId(Long produtoId);

	void deletaProduto(Long produtoId);

	void editaProduto(Long produtoId, ProdutoEditaRequest produtoEdita);

	List<ProdutoResponse> buscaTodosProdutos();

	void deletaTodosProdutos();
}
