package com.kingstar.curso.domain.exception.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	CORPO_INCOMPREENSIVEL("/corpo-incompreensivel", "Corpo incompreensivel"),
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
	NEGOCIO("/negocio-nao-encontrado", "Negocio não encontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),	
	IGNORE_EXCEPTION("/ignore-exception", "Fora de requisição do Restaurante");
	
	private String title;
	private String uri;
	
	private ProblemType(String path, String title) {
		this.uri = "https://kingstar.com.br"+ path;
		this.title = title;
	}
}
