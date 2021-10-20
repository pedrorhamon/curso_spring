package com.kingstar.curso.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kingstar.curso.domain.entity.Cidade;
import com.kingstar.curso.domain.repository.CidadeRepository;

@Repository
public class CidadeRepositoryImpl implements CidadeRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Cidade> listar() {
		return manager.createQuery("from Cidade", Cidade.class).getResultList();
	}

	@Override
	public Cidade buscar(Long id) {
		return manager.find(Cidade.class, id);
	}

	@Transactional
	public Cidade salvar(Cidade cozinha) {
		return manager.merge(cozinha);
	}

	@Transactional
	public void remover(Long id) {
		Cidade cidade = buscar(id);
		manager.remove(cidade);
	}
}
