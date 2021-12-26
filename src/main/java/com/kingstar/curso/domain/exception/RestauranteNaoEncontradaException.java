package com.kingstar.curso.domain.exception;

public class RestauranteNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public RestauranteNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public RestauranteNaoEncontradaException(Long estadoId) {
		this(String.format("Não existe um cadastro de Restaurante com código %d", estadoId));
	}
}
