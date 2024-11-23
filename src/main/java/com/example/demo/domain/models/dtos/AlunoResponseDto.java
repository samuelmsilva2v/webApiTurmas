package com.example.demo.domain.models.dtos;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class AlunoResponseDto {

	private UUID id;	
	private String nome;
	private String cpf;
	private String email;
	private List<TurmaResponseDto> turmas;
}
