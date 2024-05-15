package dev.joaojt.promovenda.promocao.application.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PromocaoNovaRequest {
	
    @Size(message = "A descrição da promoção deve ter entre 5 e 50 caracteres.", min = 5, max = 50)
    @NotBlank(message = "A descrição da promoção não pode ser nula ou vazia.")
	private String promocao;
    @NotNull(message = "É obrigatório informar a quantidade de compra.")
    private Integer qtdeCompra;
    @NotNull(message = "É obrigatório informar a quantidade de pagamento.")
    private Integer qtdePgto;
    private Boolean ativa = true;	
}