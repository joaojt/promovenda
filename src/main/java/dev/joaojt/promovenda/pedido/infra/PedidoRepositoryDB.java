package dev.joaojt.promovenda.pedido.infra;

import org.springframework.stereotype.Repository;

import dev.joaojt.promovenda.pedido.application.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
@RequiredArgsConstructor
public class PedidoRepositoryDB implements PedidoRepository{

}
