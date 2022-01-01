package com.kingstar.curso.domain.exception.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kingstar.curso.domain.exception.EntidadeNaoEncontradaException;
import com.kingstar.curso.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEstadoException(EntidadeNaoEncontradaException e, WebRequest resquest){
		return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, resquest);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> tratarEstadoException(NegocioException e, WebRequest resquest){
		return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, resquest);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> EntidadeNaoEncontradaException(EntidadeNaoEncontradaException e, WebRequest resquest){
		return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, resquest);
	}
	
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (body == null) {
			body = Problema.builder()
					.dataHora(LocalDateTime.now())
					.msg(status.getReasonPhrase())
					.build();
		}else if (body instanceof String) {
			body = Problema.builder()
					.dataHora(LocalDateTime.now())
					.msg((String) body)
					.build();
		}
		return new ResponseEntity<>(body, headers, status);
	}
}
