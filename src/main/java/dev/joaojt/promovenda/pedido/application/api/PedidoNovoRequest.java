package dev.joaojt.promovenda.pedido.application.api;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PedidoNovoRequest {
	
    @Size(message = "O nome do cliente deve ter entre 5 e 50 caracteres.", min = 5, max = 50)
    @NotBlank(message = "O nome do cliente não pode ser nulo ou vazio.")
	private String cliente;
    private LocalDateTime data = LocalDateTime.now();

}