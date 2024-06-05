package dev.joaojt.promovenda.produto.infra;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.joaojt.promovenda.produto.domain.Produto;

public interface ProdutoRepositoryJpa extends JpaRepository<Produto, Long>{

	Optional<Produto> findFirstByPromocaoId(Long promocaoId);

}