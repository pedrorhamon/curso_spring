package com.kingstar.curso.domain.exception.exceptionhandler;

import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.kingstar.curso.domain.exception.EntidadeEmUsoException;
import com.kingstar.curso.domain.exception.EntidadeNaoEncontradaException;
import com.kingstar.curso.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	private static final String MENSAGEM_CONFLIT = "A propriedade '%s' recebeu o valor '%s', que é de um tipo inválido. "
			+ "Corrija e informe um valor compatível com"
			+ "o tipo %s.";

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if(rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
		}
			
		ProblemType problemType = ProblemType.CORPO_INCOMPREENSIVEL;
		String detail = "O corpo da requisição está inválido ";
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String path = ex.getPath().stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));
	
		ProblemType problemType = ProblemType.CORPO_INCOMPREENSIVEL;
		String detail = String.format(MENSAGEM_CONFLIT, path,ex.getValue(), ex.getTargetType().getSimpleName());
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(ex, problem, headers, status, request);
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
		Problem problem = createProblemBuilder(status, problemType, detail).userMessage(detail).build();
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, resquest);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleExceptionInternal(EntidadeNaoEncontradaException e, WebRequest resquest){
		return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, resquest);
	}
	
	@ExceptionHandler(IgnoredPropertyException.class)
	public ResponseEntity<?> handleExceptionInternalIgnore(IgnoredPropertyException e, WebRequest resquest, HttpHeaders headers){
		Throwable rootCause = ExceptionUtils.getRootCause(e);
		
		HttpStatus status = HttpStatus.CONFLICT;
		
		if(rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, resquest);
		}
	
		ProblemType problemType = ProblemType.CORPO_INCOMPREENSIVEL;
		 String detail = String.format(MENSAGEM_CONFLIT);
		Problem problem = createProblemBuilder(status, problemType, detail).userMessage(detail).build();
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, resquest);
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
