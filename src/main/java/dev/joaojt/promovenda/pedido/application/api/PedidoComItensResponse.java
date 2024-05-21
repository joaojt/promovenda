package dev.joaojt.promovenda.pedido.application.api;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import dev.joaojt.promovenda.pedido.domain.Pedido;
import dev.joaojt.promovenda.pedidoitem.domain.PedidoItem;
import lombok.Getter;

@Getter
public class PedidoComItensResponse {

	private Long id;
	private String cliente;
	private String data;	
	private double vlrTotalPedido;
    private List<ItemResponse> itens;  

	public PedidoComItensResponse(Pedido pedido, List<PedidoItem> itensPedido) {
	    this.id = pedido.getId();
	    this.cliente = pedido.getCliente();
	    
        DateTimeFormatter formatoDesejado = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.data = pedido.getData().format(formatoDesejado);		
        
        this.vlrTotalPedido = itensPedido.stream()
                .mapToDouble(item -> item.getVlrTotal() > 0 ? item.getVlrTotal() : 0.0)
                .sum();    
        
	    this.itens = itensPedido.stream()
	                            .map(item -> new ItemResponse(
	                                item.getId(),
	                                item.getIdPedido(),
	                                item.getIdProduto(),
	                                item.getIdPromocao(),
	                                item.getQtde(),
	                                item.getVlrUnitario(),
	                                item.getVlrTotal()))
	                            .collect(Collectors.toList());
	}    

    @Getter
    public static class ItemResponse {
    	
		private Long id;
    	private Long idPedido;
    	private Long idProduto;
    	private Long idPromocao;
    	private Integer qtde;
    	private Double vlrUnitario;
    	private Double vlrTotal;	
    	
        public ItemResponse(Long id, Long idPedido, Long idProduto, Long idPromocao, int qtde, double vlrUnitario, double vlrTotal) {
            this.id = id;
            this.idPedido = idPedido;
            this.idProduto = idProduto;
            this.idPromocao = idPromocao;
            this.qtde = qtde;
            this.vlrUnitario = vlrUnitario;
            this.vlrTotal = vlrTotal;
        }  	
    }
    
}