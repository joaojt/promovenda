package dev.joaojt.promovenda.produto.application.api;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProdutoEditaRequest {
	
    @Size(message = "A descrição do produto deve ter entre 5 e 50 caracteres.", min = 5, max = 50)
    private String produto;
    private Long idPromocao;
    private Double valor;
    private Integer estoque;

}