package com.kingstar.curso.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntidadeNaoEncontradaException extends ResponseStatusException {

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(HttpStatus status, String msg) {
		super(status, msg);
	}

	public EntidadeNaoEncontradaException(String msg) {
		this(HttpStatus.NOT_FOUND, msg);
	}
}
