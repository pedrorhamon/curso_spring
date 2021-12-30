package com.kingstar.curso.domain.exception.exceptionhandler;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Problema {
	
	private LocalDateTime dataHora;
	private String msg;
}
