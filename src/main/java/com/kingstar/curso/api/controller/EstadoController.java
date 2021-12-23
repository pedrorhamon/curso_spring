package com.kingstar.curso.api.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kingstar.curso.domain.entity.Estado;
import com.kingstar.curso.domain.repository.EstadoRepository;
import com.kingstar.curso.domain.service.CadastroEstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroEstadoService estadoService;

	@GetMapping("/{estados}")
	public Estado buscar(@PathVariable Long estadoId) {
		return estadoService.buscarFalha(estadoId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado adicionar(@RequestBody Estado cozinha) {
		return estadoService.salvar(cozinha);
	}

	@PutMapping("/estados/{id}")
	public Estado atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
		Estado estadoAtual = estadoService.buscarFalha(estadoId);
		BeanUtils.copyProperties(estado, estadoAtual, "id");

			return estadoService.salvar(estadoAtual);
	}

	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) {
		estadoService.excluir(estadoId);
	}

}
