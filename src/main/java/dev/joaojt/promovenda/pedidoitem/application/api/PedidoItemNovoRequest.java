package dev.joaojt.promovenda.pedidoitem.application.api;

import java.util.Optional;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PedidoItemNovoRequest {

	private Long pedidoId;
	@NotNull(message = "É obrigatório informar o ID do produto.")
	private Long produtoId;
	private Integer qtde = 1;
	@PositiveOrZero(message = "O valor unitário deve ser igual ou maior que zero.")
	private Double vlrUnitario;

	public PedidoItemNovoRequest(Long pedidoId, Long produtoId, PedidoItemEditaRequest pedidoItemEdita) {
		this.pedidoId = pedidoId;
		this.produtoId = produtoId;
		Optional.ofNullable(pedidoItemEdita.getQtde()).ifPresent(qtde -> this.qtde = qtde);
		Optional.ofNullable(pedidoItemEdita.getVlrUnitario()).ifPresent(vlrUnitario -> this.vlrUnitario = vlrUnitario);
	}
	
}