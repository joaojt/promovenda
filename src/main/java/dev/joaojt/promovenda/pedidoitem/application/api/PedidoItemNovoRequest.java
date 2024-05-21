package dev.joaojt.promovenda.pedidoitem.application.api;

import jakarta.validation.constraints.NotNull;
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
	private int qtde = 1;
	private double vlrUnitario;
	private double vlrTotal;

}