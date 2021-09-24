package com.kingstar.curso.domain.repository;

import java.util.List;

import com.kingstar.curso.domain.entity.Restaurante;

public interface RestauranteRepository {
	
	List<Restaurante> listar();
	Restaurante buscar(Long id);
	Restaurante salvar(Restaurante restaurante);
	void remover(Restaurante restaurante);

}
