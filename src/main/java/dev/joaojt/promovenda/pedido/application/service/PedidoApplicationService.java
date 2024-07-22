package dev.joaojt.promovenda.pedido.application.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dev.joaojt.promovenda.handler.APIException;
import dev.joaojt.promovenda.pedido.application.api.PedidoComItensResponse;
import dev.joaojt.promovenda.pedido.application.api.PedidoNovoRequest;
import dev.joaojt.promovenda.pedido.application.api.PedidoResponse;
import dev.joaojt.promovenda.pedido.application.repository.PedidoRepository;
import dev.joaojt.promovenda.pedido.domain.Pedido;
import dev.joaojt.promovenda.pedidoitem.application.repository.PedidoItemRepository;
import dev.joaojt.promovenda.pedidoitem.domain.PedidoItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class PedidoApplicationService implements PedidoService{
	
	private final PedidoRepository pedidoRepository;
	private final PedidoItemRepository pedidoItemRepository;
	
	@Override
	public PedidoResponse inserePedido(PedidoNovoRequest pedidoNovo) {
		log.info("[inicia] PedidoApplicationService - inserePedido");
		Pedido pedido = pedidoRepository.salvaPedido(new Pedido(pedidoNovo));
		log.info("[finaliza] PedidoApplicationService - inserePedido");
		return new PedidoResponse(pedido);
	}

	@Override
	public void deletaPedido(Long pedidoId) {
		log.info("[inicia] PedidoApplicationService - deletaPedido");
		Pedido pedido = pedidoRepository.buscaPedidoPorId(pedidoId);
		List<PedidoItem> pedidoItens = pedidoItemRepository.buscaPedidoItens(pedidoId);
		Optional.ofNullable(pedidoItens).filter(itens -> !itens.isEmpty())
				.ifPresent(pedidoItemRepository::deletaPedidoItens);
		pedidoRepository.deletaPedido(pedido);
		log.info("[finaliza] PedidoApplicationService - deletaPedido");
	}

	@Override
	public PedidoComItensResponse buscaPedidoComItens(Long pedidoId) {
		log.info("[inicia] PedidoApplicationService - buscaPedidoComItens");
		Pedido pedido = pedidoRepository.buscaPedidoPorId(pedidoId);
		PedidoComItensResponse pedidoComItensResponse = new PedidoComItensResponse(pedido);
		log.info("[finaliza] PedidoApplicationService - buscaPedidoComItens");	
		return pedidoComItensResponse;		
	}
	
	@Override
	public List<PedidoComItensResponse> buscaPedidosComItensPorPeriodo(LocalDateTime dataInicial,LocalDateTime dataFinal) {
		log.info("[inicia] PedidoApplicationService - buscaPedidosComItensPorPeriodo");
		List<Pedido> pedidos = pedidoRepository.buscaPedidosComItensPorPeriodo(dataInicial, dataFinal);
		log.info("[finaliza] PedidoApplicationService - buscaPedidosComItensPorPeriodo");
		return pedidos.stream()
				.map(pedido -> {return new PedidoComItensResponse(pedido);
					}).collect(Collectors.toList());
	}

	@Override
	public PedidoComItensResponse fechaPedido() {
		log.info("[inicia] PedidoApplicationService - fechaPedido");
		Pedido pedido = pedidoRepository.buscaPedidoAberto();
		if (pedido != null) {
			pedido.fechaPedidoAberto();
			pedidoRepository.salvaPedido(pedido);
		} else {
			throw APIException.build(HttpStatus.BAD_REQUEST,"Não existe pedido aberto para ser fechado.");
		}
		log.info("[finaliza] PedidoApplicationService - fechaPedido");
		return new PedidoComItensResponse(pedido);
	}
	
	@Override
	public List<PedidoComItensResponse> buscaPedidosComItensPorQtde(Integer qtde) {
	    log.info("[inicia] PedidoApplicationService - buscaPedidosComItensPorQtde");		
	    
	    // Buscar os itens de pedido que atendem ao critério de quantidade:
	    // O método buscaItensPorQtde do repositório pedidoItemRepository é chamado para buscar todos os PedidoItem que atendem ao critério de quantidade especificado (qtde).
	    // A lista de itens resultante é armazenada na variável itens.
	    List<PedidoItem> itens = pedidoItemRepository.buscaItensPorQtde(qtde);
	    
	    // Inicializar um mapa para agrupar os pedidos:
	    // Um HashMap chamado pedidosMap é criado para agrupar os pedidos (Pedido) com base no ID do pedido.
	    // A chave do mapa será o ID do pedido, e o valor será o objeto Pedido.
	    Map<Long, Pedido> pedidosMap = new HashMap<>();
	    
	    // Iterar sobre os itens para buscar os pedidos desses itens:
	    // Um loop for é utilizado para iterar sobre cada PedidoItem na lista itens.
	    // Para cada item, o pedido (Pedido) associado ao item é obtido usando item.getPedido().
	    for (PedidoItem item : itens) {
	        Pedido pedido = item.getPedido();
	        
	        // Adicionar um novo pedido ao mapa, se ele ainda não estiver presente:
	        // Verifica se o pedido atual (pedido) já está presente no mapa pedidosMap usando pedidosMap.containsKey(pedido.getId()).
	        // Se o pedido não estiver presente no mapa:
	        // Um novo objeto Pedido é criado a partir do pedido atual (Pedido novoPedido = new Pedido(pedido);). 
	        // Este novo pedido possui uma lista de itens vazia, conforme definido no construtor Pedido(Pedido pedido).
            // 'pedidosMap.put(novoPedido.getId(), novoPedido)' adiciona o novo pedido ao mapa pedidosMap:
            // 'novoPedido.getId()' obtém o ID do novoPedido.
            // novoPedido é o novo pedido a ser adicionado ao mapa.
            // O método put adiciona a entrada no mapa, associando o ID do novo pedido ao próprio objeto Pedido.
	        if (!pedidosMap.containsKey(pedido.getId())) {
	            Pedido novoPedido = new Pedido(pedido); 
	            pedidosMap.put(novoPedido.getId(), novoPedido);
	        }
	        
	        // Adicionar o item atual à lista de itens do pedido correspondente no mapa:
	        // Independentemente de o pedido ter sido recém-adicionado ao mapa ou já estar presente, 
	        // esta linha garante que o item atual (PedidoItem) seja adicionado à lista de itens do pedido no mapa pedidosMap:
            // 'pedidosMap.get(pedido.getId())' obtém o Pedido do mapa pedidosMap cujo ID é pedido.getId().
            // '.getPedidoItem()' obtém a lista de itens (pedidoItem) do pedido recuperado.
            // '.add(item)' adiciona o item atual (item) à lista de itens do pedido.
	        pedidosMap.get(pedido.getId()).getPedidoItem().add(item);
	    }
	    
	    // Converter o mapa de pedidos em uma lista:
	    // Os valores do mapa pedidosMap (que são objetos Pedido) são convertidos em uma lista chamada pedidos.
	    List<Pedido> pedidos = new ArrayList<>(pedidosMap.values());
	    
	    // Converter a lista de pedidos em uma lista de respostas e retornada como resultado do método.
	    List<PedidoComItensResponse> pedidoComItensResponse = PedidoComItensResponse.converter(pedidos);
	    log.info("[finaliza] PedidoApplicationService - buscaPedidosComItensPorQtde");
	    return pedidoComItensResponse;     
	}
	
}