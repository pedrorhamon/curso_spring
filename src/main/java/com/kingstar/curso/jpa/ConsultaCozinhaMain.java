package com.kingstar.curso.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.kingstar.curso.CursoApiApplication;
import com.kingstar.curso.domain.entity.Cozinha;

public class ConsultaCozinhaMain {
	
	public static void main(String[] args) {
		ApplicationContext appC = new SpringApplicationBuilder(CursoApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CadastroCozinha cadastroCozinha = appC.getBean(CadastroCozinha.class);
		
		List<Cozinha> cozinhas = cadastroCozinha.listar();
		
		for(Cozinha cozinha: cozinhas) {
			System.out.println(cozinha.getNome());
		}
	}
}
