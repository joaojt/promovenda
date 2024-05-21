package dev.joaojt.promovenda.promocao.application.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PromocaoNovaRequest {
	
    @Size(message = "A descrição da promoção deve ter entre 5 e 50 caracteres.", min = 5, max = 50)
    @NotBlank(message = "A descrição da promoção não pode ser nula ou vazia.")
	private String promocao;
    @Positive(message = "A quantidade de compra deve ser maior que zero.")
    private int qtdeCompra;
    @Positive(message = "A quantidade de pagamento deve ser maior que zero.")
    private int qtdePgto;
    private boolean ativa = true;	
}