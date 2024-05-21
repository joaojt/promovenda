package dev.joaojt.promovenda.pedido.domain;

import java.time.LocalDateTime;

import dev.joaojt.promovenda.pedido.application.api.PedidoNovoRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Size(message = "O nome do cliente deve ter entre 5 e 50 caracteres.", min = 5, max = 50)
    @NotBlank(message = "O nome do cliente não pode ser nulo ou vazio.")
	private String cliente;
    private LocalDateTime data = LocalDateTime.now();

	public Pedido(PedidoNovoRequest pedidoNovo) {
		this.cliente = pedidoNovo.getCliente();
		this.data = pedidoNovo.getData();	
	}

}
