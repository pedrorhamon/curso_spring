package com.kingstar.curso.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.kingstar.curso.CursoApiApplication;
import com.kingstar.curso.domain.entity.Cozinha;

public class BuscarCozinhaMain {
	
	public static void main(String[] args) {
		ApplicationContext appC = new SpringApplicationBuilder(CursoApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CadastroCozinha cadastroCozinha = appC.getBean(CadastroCozinha.class);

		Cozinha cozinha = cadastroCozinha.buscar(1L);

		System.out.println(cozinha.getNome());
	}
}
