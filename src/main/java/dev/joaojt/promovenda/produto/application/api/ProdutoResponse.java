package dev.joaojt.promovenda.produto.application.api;

import java.util.List;
import java.util.stream.Collectors;

import dev.joaojt.promovenda.produto.domain.Produto;
import lombok.Getter;

@Getter
public class ProdutoResponse {
	
	private final Long id;
	private final String produto;
	private final Long idPromocao;
	private final Double valor;
	
	public ProdutoResponse(Produto produto) {
		this.id = produto.getId();
		this.produto = produto.getProduto();
		this.idPromocao = produto.getIdPromocao();
		this.valor = produto.getValor();
	}

	public static List<ProdutoResponse> converter(List<Produto> produtos) {
		return produtos.stream()
				.map(ProdutoResponse::new)
				.collect(Collectors.toList());
	}
}