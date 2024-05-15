package dev.joaojt.promovenda.promocao.application.service;

import java.util.List;

import dev.joaojt.promovenda.promocao.application.api.PromocaoAtivaInativaRequest;
import dev.joaojt.promovenda.promocao.application.api.PromocaoNovaRequest;
import dev.joaojt.promovenda.promocao.application.api.PromocaoResponse;

public interface PromocaoService {

	PromocaoResponse inserePromocao(PromocaoNovaRequest promocaoNova);

	PromocaoResponse buscaPromocaoPorId(Long idPromocao);

	void ativaInativaPromocao(Long idPromocao, PromocaoAtivaInativaRequest promocaoAtivaInativa);

	List<PromocaoResponse> buscaTodasPromocoes();

}
