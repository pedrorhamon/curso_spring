package com.kingstar.curso.domain.repository;

import java.util.List;

import com.kingstar.curso.domain.entity.Cozinha;

public interface CozinhaRepository {
	
	List<Cozinha> listar();
	List<Cozinha> consultarPorNome(String nome);
	Cozinha buscar(Long id);
	Cozinha salvar(Cozinha cozinha);
	void remover(Long id);
}
