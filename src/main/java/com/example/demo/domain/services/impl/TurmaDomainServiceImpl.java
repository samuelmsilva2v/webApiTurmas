package com.example.demo.domain.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.domain.models.dtos.TurmaRequestDto;
import com.example.demo.domain.models.dtos.TurmaResponseDto;
import com.example.demo.domain.services.interfaces.TurmaDomainService;

@Service
public class TurmaDomainServiceImpl implements TurmaDomainService {

	@Override
	public TurmaResponseDto cadastrar(TurmaRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TurmaResponseDto atualizar(UUID id, TurmaRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String excluir(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TurmaResponseDto> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TurmaResponseDto consultarPorId(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

}
