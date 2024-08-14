package dev.joaojt.promovenda.promocao.application.api;

import java.util.List;
import java.util.stream.Collectors;

import dev.joaojt.promovenda.promocao.domain.Promocao;
import lombok.Getter;

@Getter
public class PromocaoResponse {
	
	private Long id;
	private String descPromocao;
	private Integer qtdeCompra;
	private Integer qtdePgto;
	private Boolean ativa;
	
	public PromocaoResponse(Promocao promocao) {
		this.id = promocao.getId();
		this.descPromocao = promocao.getDescPromocao();
		this.qtdeCompra = promocao.getQtdeCompra();
		this.qtdePgto = promocao.getQtdePgto();
		this.ativa = promocao.getAtiva();
	}

	public static List<PromocaoResponse> converter(List<Promocao> promocoes) {
		return promocoes.stream()
				.map(PromocaoResponse::new)
				.collect(Collectors.toList());
	}
	
}