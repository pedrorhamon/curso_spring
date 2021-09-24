package com.kingstar.curso.domain.repository;

import java.util.List;

import com.kingstar.curso.domain.entity.Permissao;
import com.kingstar.curso.domain.entity.Restaurante;

public interface PermissaoRepository {
	
	List<Permissao> listar();
	Restaurante buscar(Long id);
	Permissao salvar(Permissao restaurante);
	void remover(Permissao restaurante);

}
