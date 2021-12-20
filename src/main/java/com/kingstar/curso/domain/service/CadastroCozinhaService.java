package com.kingstar.curso.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.kingstar.curso.domain.entity.Cozinha;
import com.kingstar.curso.domain.exception.EntidadeEmUsoException;
import com.kingstar.curso.domain.exception.EntidadeNaoEncontradaException;
import com.kingstar.curso.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}

	public void excluir(Long cozinhaId) {
		try {
			cozinhaRepository.deleteById(cozinhaId);
		} catch (EmptyResultDataAccessException e) {
			String MSG_COZINHA_NAO_ENCONTRADA = "Não existe um cadastro de cozinha no código %d ";
			throw new EntidadeNaoEncontradaException(
					String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Cozinha de código %d não pode ser removida, pois está em uso", cozinhaId));
		}
	}
	
	public Cozinha buscarFalha(Long cozinhaId) {
		return cozinhaRepository.findById(cozinhaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("ID não foi encontrado"));
	}
}
