package dev.joaojt.promovenda.pedido.application.repository;

import java.time.LocalDateTime;
import java.util.List;

import dev.joaojt.promovenda.pedido.domain.Pedido;

public interface PedidoRepository {

	Pedido salvaPedido(Pedido pedidoNovo);

	void deletaPedido(Pedido pedido);

	Pedido buscaPedidoPorId(Long idPedido);

	List<Pedido> buscaPedidosComItensPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal);

}