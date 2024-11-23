package com.example.demo.domain.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.domain.models.dtos.AlunoRequestDto;
import com.example.demo.domain.models.dtos.AlunoResponseDto;
import com.example.demo.domain.services.interfaces.AlunoDomainService;

@Service
public class AlunoDomainServiceImpl implements AlunoDomainService {

	@Override
	public AlunoResponseDto cadastrar(AlunoRequestDto request) {
		// TODO Auto-generated method stub
				return null;
	}

	@Override
	public AlunoResponseDto atualizar(UUID id, AlunoRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String excluir(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AlunoResponseDto> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlunoResponseDto consultarPorId(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

}
