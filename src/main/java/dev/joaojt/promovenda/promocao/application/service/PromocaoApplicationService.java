package dev.joaojt.promovenda.promocao.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.joaojt.promovenda.pedidoitem.application.repository.PedidoItemRepository;
import dev.joaojt.promovenda.produto.application.repository.ProdutoRepository;
import dev.joaojt.promovenda.promocao.application.api.PromocaoAtivaInativaRequest;
import dev.joaojt.promovenda.promocao.application.api.PromocaoEditaRequest;
import dev.joaojt.promovenda.promocao.application.api.PromocaoNovaRequest;
import dev.joaojt.promovenda.promocao.application.api.PromocaoResponse;
import dev.joaojt.promovenda.promocao.application.repository.PromocaoRepository;
import dev.joaojt.promovenda.promocao.domain.Promocao;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class PromocaoApplicationService implements PromocaoService{
	
	private final PromocaoRepository promocaoRepository;
	private final ProdutoRepository produtoRepository;
	private final PedidoItemRepository pedidoItemRepository;
	
	@Override
	public PromocaoResponse inserePromocao(PromocaoNovaRequest promocaoNova) {
		log.info("[inicia] PromocaoApplicationService - inserePromocao");
		Promocao promocao = promocaoRepository.salvaPromocao(new Promocao(promocaoNova));
		log.info("[finaliza] PromocaoApplicationService - inserePromocao");
		return new PromocaoResponse(promocao);
	}

	@Override
	public PromocaoResponse buscaPromocaoPorId(Long idPromocao) {
		log.info("[inicia] PromocaoApplicationService - buscaPromocaoPorId");
		Promocao promocao = promocaoRepository.buscaPromocaoPorId(idPromocao);
		log.info("[finaliza] PromocaoApplicationService - buscaPromocaoPorId");
		return new PromocaoResponse(promocao);
	}

	@Override
	public void ativaInativaPromocao(Long idPromocao, PromocaoAtivaInativaRequest promocaoAtivaInativa) {
		log.info("[inicia] PromocaoApplicationService - ativaInativaPromocao");
		Promocao promocao = promocaoRepository.buscaPromocaoPorId(idPromocao);
		promocao.ativaInativaPromocao(promocaoAtivaInativa);
		promocaoRepository.salvaPromocao(promocao);
		log.info("[finaliza] PromocaoApplicationService - ativaInativaPromocao");
	}
	
	@Override
	public List<PromocaoResponse> buscaTodasPromocoes() {
		log.info("[inicia] PromocaoApplicationService - buscaTodasPromocoes");
		List<Promocao> promocoes = promocaoRepository.buscaTodasPromocoes();
		log.info("[finaliza] PromocaoApplicationService - buscaTodasPromocoes");
		return PromocaoResponse.converter(promocoes);
	}

	@Override
	public void deletaPromocao(Long idPromocao) {
		log.info("[inicia] PromocaoApplicationService - deletaPromocao");
	 	Promocao promocao = promocaoRepository.buscaPromocaoPorId(idPromocao);
	 	pedidoItemRepository.buscaSeIdPromocaoExisteNaPedidoItem(idPromocao);
		produtoRepository.buscaSeIdPromocaoExisteNaProduto(idPromocao);
		promocaoRepository.deletaPromocao(promocao);
		log.info("[finaliza] PromocaoApplicationService - deletaPromocao");
	}

	@Override
	public void editaPromocao(Long idPromocao, PromocaoEditaRequest promocaoEdita) {
		log.info("[inicia] PromocaoApplicationService - editaPromocao");
	 	Promocao promocao = promocaoRepository.buscaPromocaoPorId(idPromocao);
		pedidoItemRepository.buscaSeIdPromocaoExisteNaPedidoItem(idPromocao);		
		promocao.editaPromocao(promocaoEdita);
		promocaoRepository.salvaPromocao(promocao);
		log.info("[finaliza] PromocaoApplicationService - editaPromocao");	
	}	
	
}