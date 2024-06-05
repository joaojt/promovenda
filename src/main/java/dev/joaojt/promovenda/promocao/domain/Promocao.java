package dev.joaojt.promovenda.promocao.domain;

import java.util.Optional;

import dev.joaojt.promovenda.promocao.application.api.PromocaoAtivaInativaRequest;
import dev.joaojt.promovenda.promocao.application.api.PromocaoEditaRequest;
import dev.joaojt.promovenda.promocao.application.api.PromocaoNovaRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	private String descPromocao;
    @NotNull(message = "A quantidade de compra não pode ser nula.")
    @Positive(message = "A quantidade de compra deve ser maior que zero.")
    private Integer qtdeCompra;
    @NotNull(message = "A quantidade de pagamento não pode ser nula.")
    @Positive(message = "A quantidade de pagamento deve ser maior que zero.")
    private Integer qtdePgto;
    private Boolean ativa;

	public void ativaInativaPromocao(PromocaoAtivaInativaRequest promocaoAtivaInativa) {
		this.ativa = promocaoAtivaInativa.getAtiva();	
	}

	public Promocao(PromocaoNovaRequest promocaoNova) {
		this.descPromocao = promocaoNova.getDescPromocao();
		this.qtdeCompra = promocaoNova.getQtdeCompra();
		this.qtdePgto = promocaoNova.getQtdePgto();
		this.ativa = promocaoNova.getAtiva();
	}

	public void editaPromocao(PromocaoEditaRequest promocaoEdita) {
		Optional.ofNullable(promocaoEdita.getDescPromocao()).ifPresent(descPromocao -> this.descPromocao = descPromocao);
		Optional.ofNullable(promocaoEdita.getQtdeCompra()).ifPresent(qtdeCompra -> this.qtdeCompra = qtdeCompra);
		Optional.ofNullable(promocaoEdita.getQtdePgto()).ifPresent(qtdePgto -> this.qtdePgto = qtdePgto);
		Optional.ofNullable(promocaoEdita.getAtiva()).ifPresent(ativa -> this.ativa = ativa);
	}

}