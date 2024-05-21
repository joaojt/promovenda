package dev.joaojt.promovenda.pedido.infra;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.joaojt.promovenda.pedido.domain.Pedido;

public interface PedidoRepositoryJpa extends JpaRepository<Pedido, Long>{
	
	@Query("select p from Pedido p where p.data between :dataInicial and :dataFinal")
    List<Pedido> findPedidosByPeriodo(@Param("dataInicial") LocalDateTime dataInicial, @Param("dataFinal") LocalDateTime dataFinal);

}