package com.kingstar.curso.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class FormaPagamento {
	
	@Column
	private String descricao;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurante restaurante;

}
