package com.kingstar.curso.domain.exception.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada");
	
	private String title;
	private String uri;
	
	private ProblemType(String path, String title) {
		this.uri = "https://kingstar.com.br"+ path;
		this.title = title;
	}
}
