package dev.joaojt.promovenda.pedido.application.api;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import dev.joaojt.promovenda.pedido.domain.Pedido;
import dev.joaojt.promovenda.pedidoitem.domain.PedidoItem;
import dev.joaojt.promovenda.promocao.application.repository.PromocaoRepository;
import dev.joaojt.promovenda.promocao.domain.Promocao;
import lombok.Getter;

@Getter
public class PedidoComItensResponse {

	private Long id;
	private String cliente;
	private String data;	
	private Double vlrTotalPedido;
    private List<ItemResponse> itens;  

	public PedidoComItensResponse(Pedido pedido, List<PedidoItem> itensPedido, PromocaoRepository promocaoRepository) {
		this.id = pedido.getId();
		this.cliente = pedido.getCliente();
		DateTimeFormatter formatoDesejado = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		this.data = pedido.getData().format(formatoDesejado);
		this.vlrTotalPedido = itensPedido.stream().mapToDouble(item -> item.getVlrTotal() > 0 ? item.getVlrTotal() : 0.0).sum();
		this.itens = itensPedido.stream().map(item -> {
			Promocao promocao = item.getIdPromocao() != null
					? promocaoRepository.buscaPromocaoPorId(item.getIdPromocao())
					: null;
			String descPromocao = promocao != null ? promocao.getPromocao() : null;
			return new ItemResponse(item.getId(), 
					   item.getIdPedido(), 
					   item.getIdProduto(), 
					   item.getIdPromocao(),
					   descPromocao, 
					   item.getQtde(), 
					   item.getVlrUnitario(), 
					   item.getVlrTotal());
		}).collect(Collectors.toList());	
	}

    @Getter
    public static class ItemResponse {
    	
		private Long id;
    	private Long idPedido;
    	private Long idProduto;
    	private Long idPromocao;
    	private String promocao;
    	private Integer qtde;
    	private Double vlrUnitario;
    	private Double vlrTotal;	
    	
		public ItemResponse(Long id, Long idPedido, Long idProduto, Long idPromocao, String promocao, Integer qtde,
				Double vlrUnitario, Double vlrTotal) {
			this.id = id;
			this.idPedido = idPedido;
			this.idProduto = idProduto;
			this.idPromocao = idPromocao;
			this.promocao = promocao;
			this.qtde = qtde;
			this.vlrUnitario = vlrUnitario;
			this.vlrTotal = vlrTotal;
		}	
    }
    
}