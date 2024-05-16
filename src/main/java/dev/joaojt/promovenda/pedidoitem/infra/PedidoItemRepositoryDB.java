package dev.joaojt.promovenda.pedidoitem.infra;

import org.springframework.stereotype.Repository;

import dev.joaojt.promovenda.pedidoitem.application.repository.PedidoItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
@RequiredArgsConstructor
public class PedidoItemRepositoryDB implements PedidoItemRepository{
	
}
