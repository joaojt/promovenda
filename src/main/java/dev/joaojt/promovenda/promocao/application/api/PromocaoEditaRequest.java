package dev.joaojt.promovenda.promocao.application.api;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PromocaoEditaRequest {

    @Size(message = "A descrição da promoção deve ter entre 5 e 50 caracteres.", min = 5, max = 50)
	private String descPromocao;
    @Positive(message = "A quantidade de compra deve ser maior que zero.")
    private Integer qtdeCompra;
    @Positive(message = "A quantidade de pagamento deve ser maior que zero.")
    private Integer qtdePgto;
    private Boolean ativa;
    
}