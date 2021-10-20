package com.kingstar.curso.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kingstar.curso.domain.entity.FormaPagamento;
import com.kingstar.curso.domain.repository.FormaPagamentoRepository;

@Repository
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<FormaPagamento> listar() {
		return manager.createQuery("from FormaPagamento", FormaPagamento.class).getResultList();
	}
	@Override
	public FormaPagamento buscar(Long id) {
		return manager.find(FormaPagamento.class, id);
	}

	@Transactional
	public FormaPagamento salvar(FormaPagamento cozinha) {
		return manager.merge(cozinha);
	}
	
	@Transactional
	public void remover(FormaPagamento FormaPagamento) {
		FormaPagamento = buscar(FormaPagamento.getId());
		manager.remove(FormaPagamento);
	}
}
