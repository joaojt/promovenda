package dev.joaojt.promovenda.pedidoitem.domain;

import java.util.Optional;

import dev.joaojt.promovenda.pedidoitem.application.api.PedidoItemNovoRequest;
import dev.joaojt.promovenda.produto.domain.Produto;
import dev.joaojt.promovenda.promocao.domain.Promocao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	@NotNull(message = "É obrigatório informar o ID do pedido (idPedido).")
	private Long idPedido;
	@NotNull(message = "É obrigatório informar o ID do produto (idProduto).")
	private Long idProduto;
	private Long idPromocao;
	private int qtde;
	private double vlrUnitario;
	private double vlrTotal;	
	
	public PedidoItem(PedidoItemNovoRequest pedidoItemNovo, Produto produto, Promocao promocao) {
		this.idPedido = pedidoItemNovo.getIdPedido();
		this.idProduto = pedidoItemNovo.getIdProduto();
		Optional.ofNullable(produto.getIdPromocao()).ifPresent(idPromocao -> this.idPromocao = idPromocao);
		this.qtde = pedidoItemNovo.getQtde();
		this.vlrUnitario = produto.getValor();
		this.vlrTotal = this.idPromocao != null && promocao.isAtiva()
				? calculaVlrTotalItemComPromocao(produto.getValor(), promocao.getQtdeCompra(), promocao.getQtdePgto(), pedidoItemNovo.getQtde())
				: produto.getValor() * pedidoItemNovo.getQtde();
	}

	private Double calculaVlrTotalItemComPromocao(double valorProduto, int qtdeCompra, int qtdePgto, int qtdeItens) {
		int qtdePromocao = qtdeItens / qtdeCompra;
		int qtdeRestante = qtdeItens % qtdeCompra;
		return qtdePromocao * qtdePgto * valorProduto + qtdeRestante * valorProduto;
	}

	public void incrementaPedidoItemExistente(PedidoItemNovoRequest pedidoItemNovo, Promocao promocao) {
		this.qtde = this.qtde + pedidoItemNovo.getQtde();
		this.vlrTotal = this.idPromocao != null && promocao.isAtiva()
				? calculaVlrTotalItemComPromocao(this.vlrUnitario, promocao.getQtdeCompra(), promocao.getQtdePgto(), this.qtde)
				: this.vlrUnitario * this.qtde;
	}

}