package com.kingstar.curso.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CozinhaNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public CozinhaNaoEncontradaException(Long estadoId) {
		this(String.format("Não existe um cadastro de cozinha com código %d", estadoId));
	}
}
