package dev.joaojt.promovenda.promocao.application.api;

import java.util.List;
import java.util.stream.Collectors;

import dev.joaojt.promovenda.promocao.domain.Promocao;
import lombok.Getter;

@Getter
public class PromocaoResponse {
	
	private final Long id;
	private final String promocao;
	private final int qtdeCompra;
	private final int qtdePgto;
	private final boolean ativa;
	
	public PromocaoResponse(Promocao promocao) {
		this.id = promocao.getId();
		this.promocao = promocao.getPromocao();
		this.qtdeCompra = promocao.getQtdeCompra();
		this.qtdePgto = promocao.getQtdePgto();
		this.ativa = promocao.isAtiva();
	}

	public static List<PromocaoResponse> converter(List<Promocao> promocoes) {
		return promocoes.stream()
				.map(PromocaoResponse::new)
				.collect(Collectors.toList());
	}
}