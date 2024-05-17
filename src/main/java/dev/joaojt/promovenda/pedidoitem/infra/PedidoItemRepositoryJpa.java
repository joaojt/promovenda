package dev.joaojt.promovenda.pedidoitem.infra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.joaojt.promovenda.pedidoitem.domain.PedidoItem;

public interface PedidoItemRepositoryJpa extends JpaRepository<PedidoItem, Long>{

	List<PedidoItem> findAllByIdPedidoOrderByIdAsc(Long idPedido);
	
}