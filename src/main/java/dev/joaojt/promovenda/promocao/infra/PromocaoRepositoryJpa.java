package dev.joaojt.promovenda.promocao.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.joaojt.promovenda.promocao.domain.Promocao;

public interface PromocaoRepositoryJpa extends JpaRepository<Promocao, Long>{

}