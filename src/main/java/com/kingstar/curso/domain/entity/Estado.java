package com.kingstar.curso.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Estado {
	
	@Column
	private String nome;
	
	@ManyToOne
	private Cidade cidade;
}
