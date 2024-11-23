package com.example.demo.application.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.models.dtos.TurmaRequestDto;
import com.example.demo.domain.models.dtos.TurmaResponseDto;
import com.example.demo.domain.services.interfaces.TurmaDomainService;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

	@Autowired
	private TurmaDomainService turmaDomainService;
	
	@PostMapping
	public TurmaResponseDto post(@RequestBody TurmaRequestDto request) {
		return turmaDomainService.cadastrar(request);
	}
	
	@PutMapping("{id}")
	public TurmaResponseDto put(@PathVariable UUID id, @RequestBody TurmaRequestDto request) {
		return turmaDomainService.atualizar(id, request);
	}
	
	@DeleteMapping("{id}")
	public String delete(@PathVariable UUID id) {
		return  turmaDomainService.excluir(id);
	}
	
	@GetMapping
	public List<TurmaResponseDto> getAll() {
		return turmaDomainService.listar();
	}
	
	@GetMapping("{id}")
	public TurmaResponseDto getById(@PathVariable UUID id) {
		return turmaDomainService.consultarPorId(id);
	}
}


 