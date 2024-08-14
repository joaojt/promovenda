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
	
	@GetMapping(value = "/busca/{promocaoId}")
	@ResponseStatus(code = HttpStatus.OK)
	PromocaoResponse buscaPromocaoPorId(@PathVariable Long promocaoId);
	
	@GetMapping(value = "/busca-todas")
	@ResponseStatus(code = HttpStatus.OK)
	List<PromocaoResponse> buscaTodasPromocoes();
	
	@PatchMapping(value = "/ativa-inativa/{promocaoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void ativaInativaPromocao(@PathVariable Long promocaoId,@RequestBody @Valid PromocaoAtivaInativaRequest promocaoAtivaInativa);
	
	@DeleteMapping(value = "/deleta/{promocaoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void deletaPromocao(@PathVariable Long promocaoId);
	
	@PatchMapping(value = "/edita/{promocaoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void editaPromocao(@PathVariable Long promocaoId,@RequestBody @Valid PromocaoEditaRequest promocaoEdita);
	
}
