package dev.joaojt.promovenda.promocao.application.api;

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
@RequestMapping("api/promocao")
public interface PromocaoAPI {
	
	@PostMapping(value = "/insere")
	@ResponseStatus(code = HttpStatus.CREATED)
	PromocaoResponse inserePromocao(@RequestBody @Valid PromocaoNovaRequest promocaoNova);
	
	@GetMapping(value = "/busca/{idPromocao}")
	@ResponseStatus(code = HttpStatus.OK)
	PromocaoResponse buscaPromocaoPorId(@PathVariable Long idPromocao);
	
	@GetMapping(value = "/buscatodas")
	@ResponseStatus(code = HttpStatus.OK)
	List<PromocaoResponse> buscaTodasPromocoes();
	
	@PatchMapping(value = "/ativainativa/{idPromocao}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void ativaInativaPromocao(@PathVariable Long idPromocao,@RequestBody @Valid PromocaoAtivaInativaRequest promocaoAtivaInativa);
	
	@DeleteMapping(value = "/deleta/{idPromocao}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void deletaPromocao(@PathVariable Long idPromocao);
	
}
