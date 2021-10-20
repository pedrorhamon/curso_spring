package com.kingstar.curso.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kingstar.curso.domain.entity.Permissao;
import com.kingstar.curso.domain.repository.PermissaoRepository;

@Repository
public class PermissaoRepositoryImpl implements PermissaoRepository{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Permissao> listar() {
		return manager.createQuery("from Permissao", Permissao.class).getResultList();
	}
	@Override
	public Permissao buscar(Long id) {
		return manager.find(Permissao.class, id);
	}

	@Transactional
	@Override
	public Permissao salvar(Permissao Permissao) {
		return manager.merge(Permissao);
	}
	
	@Transactional
	@Override
	public void remover(Permissao Permissao) {
		Permissao = buscar(Permissao.getId());
		manager.remove(Permissao);
	}
}
