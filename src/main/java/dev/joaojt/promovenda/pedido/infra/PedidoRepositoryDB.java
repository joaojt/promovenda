package dev.joaojt.promovenda.pedido.infra;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import dev.joaojt.promovenda.handler.APIException;
import dev.joaojt.promovenda.pedido.application.repository.PedidoRepository;
import dev.joaojt.promovenda.pedido.domain.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
@RequiredArgsConstructor
public class PedidoRepositoryDB implements PedidoRepository{
	
	private final PedidoRepositoryJpa pedidoRepositoryJpa;
	
	@Override
	public Pedido salvaPedido(Pedido pedidoNovo) {
		log.info("[inicia] PedidoRepositoryDB - salvaPedido");
		Pedido pedido = pedidoRepositoryJpa.save(pedidoNovo);
		log.info("[finaliza] PedidoRepositoryDB - salvaPedido");
		return pedido; 
	}

	@Override
	public void deletaPedido(Pedido pedido) {
		log.info("[inicia] PedidoRepositoryDB - deletaPedido");
		pedidoRepositoryJpa.delete(pedido);
		log.info("[finaliza] PedidoRepositoryDB - deletaPedido");	
	}

	@Override
	public Pedido buscaPedidoPorId(Long idPedido) {
		log.info("[inicia] PedidoRepositoryDB - buscaPedidoPorId");
		Pedido pedido = pedidoRepositoryJpa.findById(idPedido)
				.orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Pedido não encontrado."));
		log.info("[finaliza] PedidoRepositoryDB - buscaPedidoPorId");
		return pedido;
	}

}