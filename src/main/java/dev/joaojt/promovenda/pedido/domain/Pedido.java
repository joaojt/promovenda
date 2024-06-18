package dev.joaojt.promovenda.pedido.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import dev.joaojt.promovenda.pedido.application.api.PedidoNovoRequest;
import dev.joaojt.promovenda.pedidoitem.application.api.PedidoItemNovoRequest;
import dev.joaojt.promovenda.pedidoitem.domain.PedidoItem;
import dev.joaojt.promovenda.produto.domain.Produto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Size(message = "O nome do cliente deve ter entre 5 e 50 caracteres.", min = 5, max = 50)
	private String cliente;
    private LocalDateTime data;
    private Boolean aberto; 
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoItem> pedidoItem;

	public Pedido(PedidoNovoRequest pedidoNovo) {
		this.cliente = pedidoNovo.getCliente();
		this.data = pedidoNovo.getData();
		this.aberto = pedidoNovo.getAberto();
		this.pedidoItem = pedidoNovo.getPedidoItem();
	}

	public void adicionaPedidoItem(PedidoItemNovoRequest pedidoItemNovo, Produto produto) {
	    Optional<PedidoItem> pedidoItemExiste = pedidoItem.stream()
	    		.filter(item -> item.getProduto().getId().equals(pedidoItemNovo.getProdutoId()))
	    		.findFirst();
	    
	    if (pedidoItemExiste.isPresent()) {
	        PedidoItem pedidoItem = pedidoItemExiste.get();
	        pedidoItem.incrementaPedidoItemExistente(pedidoItemNovo, produto.getPromocao());
	    } else {
	        PedidoItem novoPedidoItem = new PedidoItem(this, pedidoItemNovo, produto, produto.getPromocao());
	        pedidoItem.add(novoPedidoItem);
	    }		
		
	}

	public void fechaPedidoAberto() {
		this.aberto = false;		
	}

}