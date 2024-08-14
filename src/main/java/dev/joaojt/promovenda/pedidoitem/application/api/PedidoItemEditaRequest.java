package dev.joaojt.promovenda.pedidoitem.application.api;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PedidoItemEditaRequest {

	@Positive(message = "A quantidade deve ser maior que zero.")
	private Integer qtde;
	@PositiveOrZero(message = "O valor unitário deve ser igual ou maior que zero.")
	private Double vlrUnitario;

}