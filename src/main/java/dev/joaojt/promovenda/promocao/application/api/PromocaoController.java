package dev.joaojt.promovenda.promocao.application.api;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import dev.joaojt.promovenda.promocao.application.service.PromocaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
public class PromocaoController implements PromocaoAPI{
	
	private final PromocaoService promocaoService;

	@Override
	public PromocaoResponse inserePromocao(PromocaoNovaRequest promocaoNova) {
		log.info("[inicia] PromocaoController - inserePromocao");
		PromocaoResponse promocao = promocaoService.inserePromocao(promocaoNova);
		log.info("[finaliza] PromocaoController - inserePromocao");
		return promocao;
	}
    
	@Override
	public PromocaoResponse buscaPromocaoPorId(Long idPromocao) {
		log.info("[inicia] PromocaoController - buscaPromocaoPorId");
		log.info("[idPromocao] {}", idPromocao);
		PromocaoResponse promocaoResponse = promocaoService.buscaPromocaoPorId(idPromocao);
		log.info("[finaliza] PromocaoController - buscaPromocaoPorId");
		return promocaoResponse;
	}

	@Override
	public void ativaInativaPromocao(Long idPromocao, PromocaoAtivaInativaRequest promocaoAtivaInativa) {
		log.info("[inicia] PromocaoController - ativaInativaPromocao");
		promocaoService.ativaInativaPromocao(idPromocao, promocaoAtivaInativa);
		log.info("[finaliza] PromocaoController - ativaInativaPromocao");			
	}

	@Override
	public List<PromocaoResponse> buscaTodasPromocoes() {
		log.info("[inicia] PromocaoController - buscaTodasPromocoes");			
		List<PromocaoResponse> promocoes = promocaoService.buscaTodasPromocoes();
		log.info("[finaliza] PromocaoController - buscaTodasPromocoes");			
		return promocoes;
	}

	@Override
	public void deletaPromocao(Long idPromocao) {
		log.info("[inicia] PromocaoController - deletaPromocao");			
		promocaoService.deletaPromocao(idPromocao);
		log.info("[finaliza] PromocaoController - deletaPromocao");		
	}

}