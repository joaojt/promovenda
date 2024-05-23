package dev.joaojt.promovenda.promocao.infra;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import dev.joaojt.promovenda.handler.APIException;
import dev.joaojt.promovenda.pedidoitem.application.api.PedidoItemEditaRequest;
import dev.joaojt.promovenda.pedidoitem.domain.PedidoItem;
import dev.joaojt.promovenda.promocao.application.repository.PromocaoRepository;
import dev.joaojt.promovenda.promocao.domain.Promocao;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
@RequiredArgsConstructor
public class PromocaoRepositoryDB implements PromocaoRepository{
	
	private final PromocaoRepositoryJpa promocaoRepositoryJpa;
	
	@Override
	public Promocao salvaPromocao(Promocao promocaoNova) {
		log.info("[inicia] PromocaoRepositoryDB - salvaPromocao");
		Promocao promocao = promocaoRepositoryJpa.save(promocaoNova);
		log.info("[finaliza] PromocaoRepositoryDB - salvaPromocao");
		return promocao; 
	}

	@Override
	public Promocao buscaPromocaoPorId(Long idPromocao) {
		log.info("[inicia] PromocaoRepositoryDB - buscaPromocaoPorId");
		Promocao promocao = promocaoRepositoryJpa.findById(idPromocao)
				.orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Promoção não encontrada."));
		log.info("[finaliza] PromocaoRepositoryDB - buscaPromocaoPorId");
		return promocao;
	}

	@Override
	public List<Promocao> buscaTodasPromocoes() {
		log.info("[inicia] PromocaoRepositoryDB - buscaTodasPromocoes");
		List<Promocao> promocoes = promocaoRepositoryJpa.findAll();
 		log.info("[finaliza] PromocaoRepositoryDB - buscaTodasPromocoes");
		return promocoes;
	}

	@Override
	public void deletaPromocao(Promocao promocao) {
		log.info("[inicia] PromocaoRepositoryDB - deletaPromocao");
		promocaoRepositoryJpa.delete(promocao);
		log.info("[finaliza] PromocaoRepositoryDB - deletaPromocao");		
	}

	@Override
	public Promocao buscaPromocaoParaEditarPedidoItem(PedidoItemEditaRequest pedidoItemEdita, PedidoItem pedidoItem) {
		log.info("[inicia] PromocaoRepositoryDB - buscaPromocaoParaEditarPedidoItem");
		Promocao promocao = (pedidoItemEdita.getIdPromocao() != null) 
			    ? buscaPromocaoPorId(pedidoItemEdita.getIdPromocao()) 
			    : (pedidoItem.getIdPromocao() != null) 
			    		? buscaPromocaoPorId(pedidoItem.getIdPromocao()) 
			    		: null;
		log.info("[finaliza] PromocaoRepositoryDB - buscaPromocaoParaEditarPedidoItem");		
		return promocao;
	}

}