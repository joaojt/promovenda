package dev.joaojt.promovenda.pedidoitem.infra;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.joaojt.promovenda.pedidoitem.domain.PedidoItem;

public interface PedidoItemRepositoryJpa extends JpaRepository<PedidoItem, Long>{

	List<PedidoItem> findAllByPedidoIdOrderByIdAsc(Long pedidoId);

	Optional<PedidoItem> findByPedidoIdAndProdutoId(Long pedidoId, Long produtoId);

	Optional<PedidoItem> findFirstByPromocaoId(Long promocaoId);

	Optional<PedidoItem> findFirstByProdutoId(Long produtoId);
	
    @Query("select pi from PedidoItem pi join fetch pi.pedido where pi.qtde > :qtde")
    List<PedidoItem> findItensByQtde(@Param("qtde") Integer qtde);
	
}