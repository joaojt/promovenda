package dev.joaojt.promovenda.pedidoitem.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.joaojt.promovenda.pedidoitem.domain.PedidoItem;

public interface PedidoItemRepositoryJpa extends JpaRepository<PedidoItem, Long>{

}
