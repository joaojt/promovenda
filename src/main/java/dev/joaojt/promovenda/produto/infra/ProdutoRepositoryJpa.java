package dev.joaojt.promovenda.produto.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.joaojt.promovenda.produto.domain.Produto;

public interface ProdutoRepositoryJpa extends JpaRepository<Produto, Long>{

}