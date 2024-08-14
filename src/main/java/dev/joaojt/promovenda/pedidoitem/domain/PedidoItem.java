package dev.joaojt.promovenda.pedidoitem.domain;

import dev.joaojt.promovenda.pedido.domain.Pedido;
import dev.joaojt.promovenda.pedidoitem.application.api.PedidoItemNovoRequest;
import dev.joaojt.promovenda.produto.domain.Produto;
import dev.joaojt.promovenda.promocao.domain.Promocao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
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
public class PedidoItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
    @JoinColumn(name = "pedidoId")
	private Pedido pedido;
	@ManyToOne
    @JoinColumn(name = "produtoId")
	@NotNull(message = "É obrigatório informar o ID do produto (idProduto).")	
	private Produto produto;
    @ManyToOne
    @JoinColumn(name = "promocaoId")
	private Promocao promocao;
	private Integer qtde;
	private Double vlrUnitario;
	private Double vlrTotal;	
	
	public PedidoItem(Pedido pedido, PedidoItemNovoRequest pedidoItemNovo, Produto produto, Promocao promocao) {
		this.pedido = pedido;
		this.produto = produto;
		this.promocao = promocao;
		this.qtde = pedidoItemNovo.getQtde();
		this.vlrUnitario = pedidoItemNovo.getVlrUnitario() != null
				? pedidoItemNovo.getVlrUnitario()
				: produto.getValor();
		this.vlrTotal = promocao != null && promocao.getAtiva()
				? calculaVlrTotalItemComPromocao(this.vlrUnitario, promocao.getQtdeCompra(), promocao.getQtdePgto(), pedidoItemNovo.getQtde())
				: this.vlrUnitario * pedidoItemNovo.getQtde();
	}

	public void incrementaPedidoItemExistente(PedidoItemNovoRequest pedidoItemNovo, Promocao promocao) {
		this.qtde += pedidoItemNovo.getQtde();
		this.vlrUnitario = pedidoItemNovo.getVlrUnitario() != null 
				? pedidoItemNovo.getVlrUnitario()
				: this.vlrUnitario;
		this.vlrTotal = promocao != null && promocao.getAtiva()
				? calculaVlrTotalItemComPromocao(this.vlrUnitario, promocao.getQtdeCompra(), promocao.getQtdePgto(), this.qtde)
				: this.vlrUnitario * this.qtde;
	}	

	private Double calculaVlrTotalItemComPromocao(double valorProduto, int qtdeCompra, int qtdePgto, int qtdeItens) {
		int qtdePromocao = qtdeItens / qtdeCompra;
		int qtdeRestante = qtdeItens % qtdeCompra;
		return qtdePromocao * qtdePgto * valorProduto + qtdeRestante * valorProduto;
	}

}