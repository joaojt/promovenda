package dev.joaojt.promovenda.pedido.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/pedido")
public interface PedidoAPI {
	
	@PostMapping(value = "/insere")
	@ResponseStatus(code = HttpStatus.CREATED)
	PedidoResponse inserePedido(@RequestBody @Valid PedidoNovoRequest pedidoNovo);

	@DeleteMapping(value = "/deleta/{idPedido}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void deletaPedido(@PathVariable Long idPedido);
	
	@GetMapping(value = "/buscapedidocomitens/{idPedido}")
	@ResponseStatus(code = HttpStatus.OK)
	PedidoComItensResponse buscaPedidoComItens(@PathVariable Long idPedido);
}