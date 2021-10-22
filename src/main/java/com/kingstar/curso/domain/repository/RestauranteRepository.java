package com.kingstar.curso.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kingstar.curso.domain.entity.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long>{
	
}
