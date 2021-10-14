package com.kingstar.curso.domain.repository;

import java.util.List;

import com.kingstar.curso.domain.entity.Permissao;

public interface PermissaoRepository {
	
	List<Permissao> listar();
	Permissao buscar(Long id);
	Permissao salvar(Permissao permissao);
	void remover(Permissao permissao);

}
