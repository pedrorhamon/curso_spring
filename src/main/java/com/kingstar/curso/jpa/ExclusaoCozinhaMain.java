package com.kingstar.curso.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.kingstar.curso.CursoApiApplication;
import com.kingstar.curso.domain.entity.Cozinha;
import com.kingstar.curso.domain.repository.CozinhaRepository;

public class ExclusaoCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext appC = new SpringApplicationBuilder(CursoApiApplication.class).web(WebApplicationType.NONE)
				.run(args);

		CozinhaRepository cadastroCozinha = appC.getBean(CozinhaRepository.class);

		Cozinha cozinha1 = new Cozinha();
		cozinha1.setId(1L);
		
		cadastroCozinha.remover(cozinha1);
	}
}
