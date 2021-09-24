package com.kingstar.curso.domain.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

	@Id
	private Long Id;

	private String nome;

	@Column(name = "taxa_frente")
	private BigDecimal taxaFrente;
}
