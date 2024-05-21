package dev.joaojt.promovenda.promocao.domain;

import dev.joaojt.promovenda.promocao.application.api.PromocaoAtivaInativaRequest;
import dev.joaojt.promovenda.promocao.application.api.PromocaoNovaRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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
public class Promocao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Size(message = "A descrição da promoção deve ter entre 5 e 50 caracteres.", min = 5, max = 50)
    @NotBlank(message = "A descrição da promoção não pode ser nula ou vazia.")
	private String promocao;
    @Positive(message = "A quantidade de compra deve ser maior que zero.")
    private int qtdeCompra;
    @Positive(message = "A quantidade de pagamento deve ser maior que zero.")
    private int qtdePgto;
    private boolean ativa;

	public void ativaInativaPromocao(PromocaoAtivaInativaRequest promocaoAtivaInativa) {
		this.ativa = promocaoAtivaInativa.getAtiva();	
	}

	public Promocao(PromocaoNovaRequest promocaoNova) {
		this.promocao = promocaoNova.getPromocao();
		this.qtdeCompra = promocaoNova.getQtdeCompra();
		this.qtdePgto = promocaoNova.getQtdePgto();
		this.ativa = promocaoNova.isAtiva();
	}

}