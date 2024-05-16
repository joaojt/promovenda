package dev.joaojt.promovenda.produto.domain;

import java.util.Optional;

import dev.joaojt.promovenda.produto.application.api.ProdutoEditaRequest;
import dev.joaojt.promovenda.produto.application.api.ProdutoNovoRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Size(message = "A descrição do produto deve ter entre 5 e 50 caracteres.", min = 5, max = 50)
    @NotBlank(message = "A descrição do produto não pode ser nula ou vazia.")
	private String produto;
    private Long idPromocao;
    @NotNull(message = "É obrigatório informar o valor do produto.")
	private Double valor;
    private Integer estoque = 0;

	public void editaProduto(ProdutoEditaRequest produtoEdita) {
		Optional.ofNullable(produtoEdita.getProduto()).ifPresent(produto -> this.produto = produto);	
		Optional.ofNullable(produtoEdita.getIdPromocao()).ifPresent(idPromocao -> this.idPromocao = idPromocao);	
		Optional.ofNullable(produtoEdita.getValor()).ifPresent(valor -> this.valor = valor);	
		Optional.ofNullable(produtoEdita.getEstoque()).ifPresent(estoque -> this.estoque = estoque);	
	
	}

	public Produto(ProdutoNovoRequest produtoNovo) {
		this.produto = produtoNovo.getProduto();
		Optional.ofNullable(produtoNovo.getIdPromocao()).ifPresent(idPromocao -> this.idPromocao = idPromocao);	
		this.valor = produtoNovo.getValor();
		Optional.ofNullable(produtoNovo.getEstoque()).ifPresent(estoque -> this.estoque = estoque);	
	}

}