package com.kingstar.curso.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Grupo {
	
	@Id
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@ManyToMany
	@Column(nullable = false)
	private Permissao permissao;
}
