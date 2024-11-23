package com.example.demo.domain.services.interfaces;

import java.util.List;
import java.util.UUID;

import com.example.demo.domain.models.dtos.TurmaRequestDto;
import com.example.demo.domain.models.dtos.TurmaResponseDto;

public interface TurmaDomainService {

	TurmaResponseDto cadastrar(TurmaRequestDto request);

	TurmaResponseDto atualizar(UUID id, TurmaRequestDto request);

	String excluir(UUID id);

	List<TurmaResponseDto> listar();

	TurmaResponseDto consultarPorId(UUID id);
}
