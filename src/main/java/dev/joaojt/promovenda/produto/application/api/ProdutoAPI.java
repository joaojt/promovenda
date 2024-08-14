package dev.joaojt.promovenda.produto.application.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;


@RestController
@RequestMapping("api/produto")
public interface ProdutoAPI {
	
	@PostMapping(value = "/insere")
	@ResponseStatus(code = HttpStatus.CREATED)
	ProdutoResponse insereProduto(@RequestBody @Valid ProdutoNovoRequest produtoNovo);
	
	@DeleteMapping(value = "/deleta/{produtoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void deletaProduto(@PathVariable Long produtoId);
	
	@DeleteMapping(value = "/deleta-todos")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void deletaTodosProdutos();
	
	@GetMapping(value = "/busca/{produtoId}")
	@ResponseStatus(code = HttpStatus.OK)
	ProdutoResponse buscaProdutoPorId(@PathVariable Long produtoId);
	
	@GetMapping(value = "/busca-todos")
	@ResponseStatus(code = HttpStatus.OK)
	List<ProdutoResponse> buscaTodosProdutos();
	
	@PatchMapping(value = "/edita/{produtoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void editaProduto(@PathVariable Long produtoId,@RequestBody @Valid ProdutoEditaRequest produtoEdita);
	
}
