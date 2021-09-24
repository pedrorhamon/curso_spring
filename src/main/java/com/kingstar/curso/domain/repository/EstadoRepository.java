package com.kingstar.curso.domain.repository;

import java.util.List;

import com.kingstar.curso.domain.entity.Estado;

public interface EstadoRepository {
	
	List<Estado> listar();
	Estado buscar(Long id);
	Estado salvar(Estado restaurante);
	void remover(Estado restaurante);

}
