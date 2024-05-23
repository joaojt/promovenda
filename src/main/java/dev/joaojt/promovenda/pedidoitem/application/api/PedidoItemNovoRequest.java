package dev.joaojt.promovenda.pedidoitem.application.api;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PedidoItemNovoRequest {

	@NotNull(message = "É obrigatório informar o ID do pedido (idPedido).")
	private Long idPedido;
	@NotNull(message = "É obrigatório informar o ID do produto (idProduto).")
	private Long idProduto;
	private Integer qtde = 1;
	@PositiveOrZero(message = "O valor unitário deve ser igual ou maior que zero.")
	private Double vlrUnitario;

}