package com.example.demo.domain.models.dtos;

import java.util.List;

import lombok.Data;

@Data
public class AlunoRequestDto {

	private String nome;
	private String cpf;
	private String email;
	private List<String> turmasNumeros;
}
