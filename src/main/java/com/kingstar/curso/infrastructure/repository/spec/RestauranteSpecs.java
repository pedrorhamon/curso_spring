package com.kingstar.curso.infrastructure.repository.spec;

import org.springframework.data.jpa.domain.Specification;

import com.kingstar.curso.domain.entity.Restaurante;

public class RestauranteSpecs {
	
	public static Specification<Restaurante> comFreteGratis(){
		return new RestauranteComFreteGratisSpec();
	}
	
	public static Specification<Restaurante> comNomeSemelhante(String nome){
		return new RestauranteComNomeSemelhanteSpec(nome);
	}

}
