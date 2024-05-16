package dev.joaojt.promovenda.produto.application.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProdutoNovoRequest {
	
    @Size(message = "A descrição do produto deve ter entre 5 e 50 caracteres.", min = 5, max = 50)
    @NotBlank(message = "A descrição do produto não pode ser nula ou vazia.")
	private String produto;
	private Long idPromocao;
	@NotNull(message = "É obrigatório informar o valor do produto.")
	private Double valor;
	private Integer estoque = 0;
	
}