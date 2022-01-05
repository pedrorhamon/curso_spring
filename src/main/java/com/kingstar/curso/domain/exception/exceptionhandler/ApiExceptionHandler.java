package com.kingstar.curso.domain.exception.exceptionhandler;

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
	public ResponseEntity<?> handleExceptionInternalNaoEncontrada(EntidadeNaoEncontradaException e, WebRequest resquest){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
		String detail = e.getMessage();
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, resquest);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleExceptionInternal(NegocioException e, WebRequest resquest){
		return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, resquest);
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
