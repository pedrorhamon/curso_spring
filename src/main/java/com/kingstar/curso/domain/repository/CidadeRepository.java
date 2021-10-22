package com.kingstar.curso.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kingstar.curso.domain.entity.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{
	
}
