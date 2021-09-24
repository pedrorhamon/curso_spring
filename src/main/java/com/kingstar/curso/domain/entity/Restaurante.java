package com.kingstar.curso.domain.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

	@Id
	private Long Id;

	@Column(nullable = false)
	private String nome;

	@Column(name = "taxa_frente", nullable = false)
	private BigDecimal taxaFrente;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Cozinha cozinha;
}
