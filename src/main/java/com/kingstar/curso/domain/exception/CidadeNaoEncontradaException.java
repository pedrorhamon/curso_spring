package com.kingstar.curso.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CidadeNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public CidadeNaoEncontradaException(Long estadoId) {
		this(String.format("Não existe um cadastro de cidade com código %d", estadoId));
	}
}
