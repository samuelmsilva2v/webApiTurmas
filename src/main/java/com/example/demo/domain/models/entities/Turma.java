package com.example.demo.domain.models.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_turma")
@Data
public class Turma {

	@Id
	private UUID id;
	
	@Column(length = 4, nullable = false)
	private String numero;
	
	@Column(length = 1, nullable = false)
	private Integer anoLetivo;

	@ManyToMany(mappedBy = "turmas")
	private List<Aluno> alunos;	
}
