package dev.joaojt.promovenda.pedido.application.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dev.joaojt.promovenda.pedidoitem.domain.PedidoItem;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PedidoNovoRequest {
	
    @Size(message = "O nome do cliente deve ter entre 5 e 50 caracteres.", min = 5, max = 50)
	private String cliente = "Cliente não informado";
    private LocalDateTime data = LocalDateTime.now();
    private Boolean aberto = true;
    private List<PedidoItem> pedidoItem = new ArrayList<>();

}