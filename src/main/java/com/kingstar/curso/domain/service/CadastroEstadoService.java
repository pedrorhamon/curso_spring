package com.kingstar.curso.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.kingstar.curso.domain.entity.Cidade;
import com.kingstar.curso.domain.entity.Estado;
import com.kingstar.curso.domain.exception.EntidadeEmUsoException;
import com.kingstar.curso.domain.exception.EntidadeNaoEncontradaException;
import com.kingstar.curso.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	private static final String MSG_ESTADO_DE_CÓDIGO_D_NÃO = "Estado de código %d não pode ser removida, pois está em uso";
	@Autowired
	private EstadoRepository estadoRepository;

	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}

	public void excluir(Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de cozinha no código %d ", estadoId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_ESTADO_DE_CÓDIGO_D_NÃO, estadoId));
		}
	}
	public Estado buscarFalha(Long cidadeId) {
		return estadoRepository.findById(cidadeId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("ID não foi encontrado"));
	}
}
