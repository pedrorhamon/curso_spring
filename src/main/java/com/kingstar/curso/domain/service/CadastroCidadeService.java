package com.kingstar.curso.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.kingstar.curso.domain.entity.Cidade;
import com.kingstar.curso.domain.exception.CidadeNaoEncontradaException;
import com.kingstar.curso.domain.exception.EntidadeEmUsoException;
import com.kingstar.curso.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {

	private static final String MSG_CIDADE_DE_CÓDIGO_D_NÃO_PODE_SER_REMOVIDA = "Cidade de código %d não pode ser removida, pois está em uso";

	@Autowired
	private CidadeRepository cidadeRepository;

	public Cidade salvar(Cidade cidade) {
		return cidadeRepository.save(cidade);
	}

	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);
		} catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException(cidadeId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_CIDADE_DE_CÓDIGO_D_NÃO_PODE_SER_REMOVIDA, cidadeId));
		}
	}

	public Cidade buscarFalha(Long cidadeId) {
		return cidadeRepository.findById(cidadeId).orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
	}
}
