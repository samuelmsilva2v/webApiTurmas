package com.example.demo.domain.services.interfaces;

import java.util.List;
import java.util.UUID;

import com.example.demo.domain.models.dtos.AlunoRequestDto;
import com.example.demo.domain.models.dtos.AlunoResponseDto;

public interface AlunoDomainService {

	AlunoResponseDto cadastrar(AlunoRequestDto request);
	
	AlunoResponseDto atualizar(UUID id, AlunoRequestDto request);
	
	String excluir(UUID id);
	
	List<AlunoResponseDto> listar();
	
	AlunoResponseDto consultarPorId(UUID id);
}
