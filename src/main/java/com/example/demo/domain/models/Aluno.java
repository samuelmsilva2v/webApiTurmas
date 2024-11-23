package com.example.demo.domain.models;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_aluno")
@Data
public class Aluno {

	@Id
	private UUID id;
	
	@Column(length = 100, nullable = false)
	private String nome;
	
	@Column(length = 11, nullable = false)
	private String cpf;
	
	@Column(length = 100, nullable = false)
	private String email;
	
	@ManyToMany
    private List<Turma> turmas;
}
