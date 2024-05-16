package dev.joaojt.promovenda.pedido.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.joaojt.promovenda.pedido.domain.Pedido;

public interface PedidoRepositoryJpa extends JpaRepository<Pedido, Long>{

}
