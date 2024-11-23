package com.example.demo.domain.services.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.models.dtos.AlunoRequestDto;
import com.example.demo.domain.models.dtos.AlunoResponseDto;
import com.example.demo.domain.services.interfaces.AlunoDomainService;
import com.example.demo.infrastructure.repositories.AlunoRepository;

@Service
public class AlunoDomainServiceImpl implements AlunoDomainService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
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
