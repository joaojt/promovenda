package dev.joaojt.promovenda.produto.application.api;

import java.util.List;
import java.util.stream.Collectors;

import dev.joaojt.promovenda.produto.domain.Produto;
import dev.joaojt.promovenda.promocao.domain.Promocao;
import lombok.Getter;

@Getter
public class ProdutoResponse {
	
	private Long id;
	private String descProduto;
	private Promocao promocao;
	private Double valor;
	private Integer estoque;
	
	public ProdutoResponse(Produto produto) {
		this.id = produto.getId();
		this.descProduto = produto.getDescProduto();
		this.promocao = produto.getPromocao();
		this.valor = produto.getValor();
		this.estoque = produto.getEstoque();
	}

	public static List<ProdutoResponse> converter(List<Produto> produtos) {
		return produtos.stream()
				.map(ProdutoResponse::new)
				.collect(Collectors.toList());
	}
}