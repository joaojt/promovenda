package dev.joaojt.promovenda.produto.domain;

import java.util.Optional;

import org.springframework.http.HttpStatus;

import dev.joaojt.promovenda.handler.APIException;
import dev.joaojt.promovenda.pedidoitem.application.api.PedidoItemEditaRequest;
import dev.joaojt.promovenda.pedidoitem.application.api.PedidoItemNovoRequest;
import dev.joaojt.promovenda.pedidoitem.domain.PedidoItem;
import dev.joaojt.promovenda.produto.application.api.ProdutoEditaRequest;
import dev.joaojt.promovenda.produto.application.api.ProdutoNovoRequest;
import dev.joaojt.promovenda.promocao.domain.Promocao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
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
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Size(message = "A descrição do produto deve ter entre 5 e 50 caracteres.", min = 5, max = 50)
    @NotBlank(message = "A descrição do produto não pode ser nula ou vazia.")
	private String descProduto;
    @ManyToOne
    @JoinColumn(name = "promocaoId")
    private Promocao promocao;
	private Double valor;
    private Integer estoque;

	public void editaProduto(ProdutoEditaRequest produtoEdita, Promocao promocao) {
		Optional.ofNullable(produtoEdita.getDescProduto()).ifPresent(descProduto -> this.descProduto = descProduto);
		Optional.ofNullable(promocao).ifPresent(ePromocao -> this.promocao = promocao);
		Optional.ofNullable(produtoEdita.getValor()).ifPresent(valor -> this.valor = valor);
		Optional.ofNullable(produtoEdita.getEstoque()).ifPresent(estoque -> this.estoque = estoque);
	}

	public Produto(ProdutoNovoRequest produtoNovo, Promocao promocao) {
		this.descProduto = produtoNovo.getDescProduto();
		Optional.ofNullable(promocao).ifPresent(ePromocao -> this.promocao = promocao);
		Optional.ofNullable(produtoNovo.getValor()).ifPresent(valor -> this.valor = valor);
		Optional.ofNullable(produtoNovo.getEstoque()).ifPresent(estoque -> this.estoque = estoque);
	}

	public void editaEstoqueSubtrai(PedidoItemNovoRequest pedidoItemNovo) {
		this.estoque -= pedidoItemNovo.getQtde();
	}

	public void editaEstoqueSoma(Integer qtde) {
		this.estoque += qtde;
	}

	public void validaEstoque(PedidoItemNovoRequest pedidoItemNovo) {
		if (this.estoque < pedidoItemNovo.getQtde()) {
	        throw APIException.build(HttpStatus.BAD_REQUEST, "Estoque insuficiente para o produto: " + this.descProduto + ".");
		}
	}

	public void validaEstoqueEditaPedidoItem(PedidoItemEditaRequest pedidoItemEdita, PedidoItem pedidoItem) {
        if (this.estoque + pedidoItem.getQtde() < pedidoItemEdita.getQtde()) {
            throw APIException.build(HttpStatus.BAD_REQUEST, "Estoque insuficiente para o produto: " + this.descProduto + ".");
        }
        this.estoque += pedidoItem.getQtde() - pedidoItemEdita.getQtde();		
	}

}