package com.kingstar.curso.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import lombok.Data;

@Data
@Entity
public class Permissao {
	
	@Column
	private String nome;
	
	@Column
	@JoinColumn(nullable = false)
	private String descricao;

}
