package dev.joaojt.promovenda.pedido.infra;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.joaojt.promovenda.pedido.domain.Pedido;

public interface PedidoRepositoryJpa extends JpaRepository<Pedido, Long>{
	
	@Query("select p from Pedido p where p.data between :dataInicial and :dataFinal")
    List<Pedido> findPedidosByPeriodo(@Param("dataInicial") LocalDateTime dataInicial, @Param("dataFinal") LocalDateTime dataFinal);

	@EntityGraph(attributePaths = {"pedidoItem", "pedidoItem.promocao"})
	Pedido findByAberto(boolean b);
	
    @EntityGraph(attributePaths = {"pedidoItem", "pedidoItem.promocao"})
    Optional<Pedido> findById(Long pedidoId);

}