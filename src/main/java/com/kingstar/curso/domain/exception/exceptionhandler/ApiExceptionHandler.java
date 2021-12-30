package com.kingstar.curso.domain.exception.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kingstar.curso.domain.exception.EntidadeNaoEncontradaException;
import com.kingstar.curso.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEstadoException(EntidadeNaoEncontradaException e){
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.msg(e.getMessage()).build();
	
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(problema);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> tratarEstadoException(NegocioException e){
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.msg(e.getMessage()).build();
	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(problema);
	}
	
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<?> tratarHttpMediaTypeNotSupportedException(){
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.msg("O Tipo de midia é em válido!").build();
	
		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
				.body(problema);	
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> EntidadeNaoEncontradaException(){
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.msg("Ah um conflito").build();
	
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(problema);
	}
}
