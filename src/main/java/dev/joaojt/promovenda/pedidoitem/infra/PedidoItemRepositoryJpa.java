package dev.joaojt.promovenda.pedidoitem.infra;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.joaojt.promovenda.pedidoitem.domain.PedidoItem;

public interface PedidoItemRepositoryJpa extends JpaRepository<PedidoItem, Long>{

	List<PedidoItem> findAllByIdPedidoOrderByIdAsc(Long idPedido);

	Optional<PedidoItem> findByIdPedidoAndIdProduto(Long idPedido, Long idProduto);

	Optional<PedidoItem> findFirstByIdPromocao(Long idPromocao);

	Optional<PedidoItem> findFirstByIdProduto(Long idProduto);
	
}