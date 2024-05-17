package dev.joaojt.promovenda.pedido.application.api;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import dev.joaojt.promovenda.pedido.domain.Pedido;
import dev.joaojt.promovenda.pedidoitem.domain.PedidoItem;
import lombok.Getter;

@Getter
public class PedidoComItensResponse {

	private Long id;
	private String cliente;
	private Date data;
    private List<ItemResponse> itens;  

	public PedidoComItensResponse(Pedido pedido, List<PedidoItem> itensPedido) {
	    this.id = pedido.getId();
	    this.cliente = pedido.getCliente();
	    this.data = pedido.getData();
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
    	
        public ItemResponse(Long id, Long idPedido, Long idProduto, Long idPromocao, Integer qtde, Double vlrUnitario, Double vlrTotal) {
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