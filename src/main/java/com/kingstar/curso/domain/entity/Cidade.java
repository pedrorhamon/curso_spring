package com.kingstar.curso.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Cidade {
	
	@Column
	private String nome;

}
