package com.kingstar.curso.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kingstar.curso.domain.entity.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long>{
	
}
