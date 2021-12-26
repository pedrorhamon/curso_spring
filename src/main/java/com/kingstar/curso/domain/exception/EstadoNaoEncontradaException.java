package com.kingstar.curso.domain.exception;

public class EstadoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public EstadoNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public EstadoNaoEncontradaException(Long estadoId) {
		this(String.format("Não existe um cadastro de estado com código %d", estadoId));
	}
}
