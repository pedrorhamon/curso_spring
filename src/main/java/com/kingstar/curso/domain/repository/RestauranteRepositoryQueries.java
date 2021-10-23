package com.kingstar.curso.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.kingstar.curso.domain.entity.Restaurante;

public interface RestauranteRepositoryQueries {

	List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

}