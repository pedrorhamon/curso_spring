package com.kingstar.curso.api.controller;

import java.lang.reflect.Field;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingstar.curso.domain.entity.Restaurante;
import com.kingstar.curso.domain.exception.EntidadeNaoEncontradaException;
import com.kingstar.curso.domain.exception.NegocioException;
import com.kingstar.curso.domain.repository.RestauranteRepository;
import com.kingstar.curso.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService restauranteService;

	@GetMapping("/{restauranteId}")
	public Restaurante buscar(@PathVariable Long restauranteId) {
		return restauranteService.buscarFalha(restauranteId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante adicionar(@RequestBody @Validated Restaurante restaurante) {

		try {
			return restauranteService.salvar(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("/restaurantes/{id}")
	public Restaurante atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante, HttpServletRequest requestR) {
		Restaurante restauranteAtual = restauranteService.buscarFalha(restauranteId);
		BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
		try {
			return restauranteService.salvar(restauranteAtual);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@DeleteMapping("/{restauranteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long restauranteId) {
		restauranteService.excluir(restauranteId);
	}
	
	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest requestR) {
		ServletServerHttpRequest server = new ServletServerHttpRequest(requestR);
		try {			
			ObjectMapper object = new ObjectMapper();
			object.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			
			Restaurante restauranteOrigem = object.convertValue(dadosOrigem, Restaurante.class);
			
			dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
				Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
				field.setAccessible(true);
				
				Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
				ReflectionUtils.setField(field, restauranteDestino, novoValor);
				
			});
		}catch(IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, server);
		}
	}
}
