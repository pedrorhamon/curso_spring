package com.kingstar.curso.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kingstar.curso.domain.entity.Estado;
import com.kingstar.curso.domain.repository.EstadoRepository;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Estado> listar() {
		return manager.createQuery("from Estado", Estado.class).getResultList();
	}

	@Override
	public Estado buscar(Long id) {
		return manager.find(Estado.class, id);
	}

	@Transactional
	public Estado salvar(Estado estado) {
		return manager.merge(estado);
	}

	@Transactional
	public void remover(Long id) {
		Estado estado = buscar(id);
		manager.remove(estado);
	}
}
