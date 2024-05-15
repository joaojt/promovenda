package dev.joaojt.promovenda.promocao.application.api;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PromocaoAtivaInativaRequest {

	@NotNull(message = "Informe 'True' para ativar ou 'False' para inativar a promoção.")
    private Boolean ativa;

}