package com.example.demo.domain.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.models.dtos.TurmaRequestDto;
import com.example.demo.domain.models.dtos.TurmaResponseDto;
import com.example.demo.domain.models.entities.Turma;
import com.example.demo.domain.services.interfaces.TurmaDomainService;
import com.example.demo.infrastructure.repositories.TurmaRepository;

@Service
public class TurmaDomainServiceImpl implements TurmaDomainService {
	
	@Autowired
	private TurmaRepository turmaRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public TurmaResponseDto cadastrar(TurmaRequestDto request) {
		
		var turma = modelMapper.map(request, Turma.class);
		turma.setId(UUID.randomUUID());
		
		turmaRepository.save(turma);
		
		return modelMapper.map(turma, TurmaResponseDto.class);
	}

	@Override
	public TurmaResponseDto atualizar(UUID id, TurmaRequestDto request) {
		
		var turma = turmaRepository.findById(id).get();
		
		modelMapper.map(request, turma);
		
		return modelMapper.map(turma, TurmaResponseDto.class);
	}

	@Override
	public String excluir(UUID id) {
		
		var turma = turmaRepository.findById(id).get();
		
		turmaRepository.delete(turma);
		
		return "Turma excluída com sucesso!";
	}

	@Override
	public List<TurmaResponseDto> listar() {
		
		return turmaRepository.findAll().stream().map(turma -> modelMapper.map(turma, TurmaResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public TurmaResponseDto consultarPorId(UUID id) {
		
		var turma = turmaRepository.findById(id).get();
		
		return modelMapper.map(turma, TurmaResponseDto.class);
	}

}
