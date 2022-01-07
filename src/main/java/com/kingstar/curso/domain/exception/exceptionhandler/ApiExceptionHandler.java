package com.kingstar.curso.domain.exception.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kingstar.curso.domain.exception.EntidadeEmUsoException;
import com.kingstar.curso.domain.exception.EntidadeNaoEncontradaException;
import com.kingstar.curso.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
	
		ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
		String detail = "O corpo da requisição está inválido ";
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleExceptionInternalNaoEncontrada(EntidadeNaoEncontradaException e, WebRequest resquest){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
		String detail = e.getMessage();
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, resquest);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleExceptionInternalNegocio(NegocioException e, WebRequest resquest){
		
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.NEGOCIO;
		String detail = e.getMessage();
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, resquest);
	}
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleExceptionInternalEmUso(EntidadeEmUsoException e, WebRequest resquest){
		
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		String detail = e.getMessage();
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, resquest);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleExceptionInternal(EntidadeNaoEncontradaException e, WebRequest resquest){
		return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, resquest);
	}
	
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (body == null) {
			body = Problem.builder()
					.title(status.getReasonPhrase())
					.status(status.value())
					.build();
		}else if (body instanceof String) {
			body = Problem.builder()
					.title((String) body)
					.status(status.value())
					.build();
		}
		return new ResponseEntity<>(body, headers, status);
	}
	
	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
		return Problem.builder()
				.status(status.value())
				.type(problemType.getUri())
				.title(problemType.getTitle())
				.detail(detail);
		
	}
}
