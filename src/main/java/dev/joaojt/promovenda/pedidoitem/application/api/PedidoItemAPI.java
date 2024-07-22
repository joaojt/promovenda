package dev.joaojt.promovenda.pedidoitem.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.joaojt.promovenda.pedido.application.api.PedidoComItensResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/pedido-item")
public interface PedidoItemAPI {
	
	@PostMapping(value = "/insere")
	@ResponseStatus(code = HttpStatus.CREATED)
	PedidoComItensResponse inserePedidoItem(@RequestBody @Valid PedidoItemNovoRequest pedidoItemNovo);
	
	@DeleteMapping(value = "/deleta/pedidoid/{pedidoId}/produtoid/{produtoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void deletaPedidoItem(@PathVariable Long pedidoId, @PathVariable Long produtoId);
	
	@PatchMapping(value = "/edita/pedidoid/{pedidoId}/produtoid/{produtoId}")
	@ResponseStatus(code = HttpStatus.OK)
	PedidoComItensResponse editaPedidoItem(@PathVariable Long pedidoId, @PathVariable Long produtoId,
			@RequestBody @Valid PedidoItemEditaRequest pedidoItemEdita);

}