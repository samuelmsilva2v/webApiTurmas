package com.example.demo.domain.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.domain.models.dtos.AlunoRequestDto;
import com.example.demo.domain.models.dtos.AlunoResponseDto;
import com.example.demo.domain.models.entities.Aluno;
import com.example.demo.domain.models.entities.Turma;
import com.example.demo.domain.services.interfaces.AlunoDomainService;
import com.example.demo.infrastructure.repositories.AlunoRepository;
import com.example.demo.infrastructure.repositories.TurmaRepository;

@Service
public class AlunoDomainServiceImpl implements AlunoDomainService {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private TurmaRepository turmaRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public AlunoResponseDto cadastrar(AlunoRequestDto request) {

		// Verificar se o CPF já está cadastrado
		if (alunoRepository.existsByCpf(request.getCpf()))
			throw new DataIntegrityViolationException("CPF já cadastrado.");

		// Verificar se o e-mail já está cadastrado
		if (alunoRepository.existsByEmail(request.getEmail()))
			throw new DataIntegrityViolationException("E-mail já cadastrado.");

		// Recuperar turmas pelos IDs fornecidos no request
		List<Turma> turmas = turmaRepository.findAllById(request.getTurmasIds());

		// Verificar se há turmas associadas
		if (turmas.isEmpty())
			throw new IllegalArgumentException("O aluno deve estar associado a pelo menos uma turma.");

		// Validar as regras de negócio
		for (Turma turma : turmas) {

			// Verificar se a turma já possui o aluno
			if (turma.getAlunos().stream().anyMatch(a -> a.getCpf().equals(request.getCpf()))) {
				throw new IllegalArgumentException("O aluno já está matriculado na turma: " + turma.getNumero());
			}

			// Verificar se a turma ainda tem espaço para mais algum aluno
			if (turma.getAlunos().size() >= 5) {
				throw new IllegalArgumentException(
						"A turma " + turma.getNumero() + " já atingiu o limite de 5 alunos.");
			}
		}

		var aluno = modelMapper.map(request, Aluno.class);
		aluno.setId(UUID.randomUUID());
		aluno.setTurmas(turmas);

		alunoRepository.save(aluno);

		return modelMapper.map(aluno, AlunoResponseDto.class);
	}

	@Override
	public AlunoResponseDto atualizar(UUID id, AlunoRequestDto request) {

		// Buscar um aluno existente pelo ID
		var aluno = alunoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado. Verifique o ID informado."));

		// Verificar se o CPF pertence a outro aluno
		if (alunoRepository.existsByCpf(request.getCpf()) && !aluno.getCpf().equals(request.getCpf()))
			throw new DataIntegrityViolationException("CPF já cadastrado para outro aluno.");

		// Verificar se o e-mail pertence a outro aluno
		if (alunoRepository.existsByEmail(request.getEmail()) && !aluno.getEmail().equals(request.getEmail()))
			throw new DataIntegrityViolationException("E-mail já cadastrado para outro aluno.");

		aluno.setNome(request.getNome());
		aluno.setCpf(request.getCpf());
		aluno.setEmail(request.getEmail());

		// Gerenciar as associações com as turmas
		List<Turma> novasTurmas = turmaRepository.findAllById(request.getTurmasIds());

		// Validar regras de negócio para cada turma
		for (Turma turma : novasTurmas) {

			// Verificar se a turma excede o limite de 5 alunos
			if (!turma.getAlunos().contains(aluno) && turma.getAlunos().size() >= 5) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
						"A turma " + turma.getNumero() + " já atingiu o limite de 5 alunos.");
			}
		}

		aluno.setTurmas(novasTurmas);

		alunoRepository.save(aluno);

		return modelMapper.map(aluno, AlunoResponseDto.class);
	}

	@Override
	public String excluir(UUID id) {

		// Buscar um aluno existente pelo ID
		var aluno = alunoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado. Verifique o ID informado."));

		// Verificar as turmas associadas ao aluno
		List<Turma> turmas = aluno.getTurmas();

		// Validar as regras de negócio
		if (turmas.isEmpty()) {
			alunoRepository.deleteById(id);
			return "O aluno foi excluído com sucesso";
		} else {
			throw new DataIntegrityViolationException(
					"Para ser excluído, o aluno não pode estar associado a nenhuma turma.");
		}
	}

	@Override
	public List<AlunoResponseDto> listar() {
		return alunoRepository.findAll().stream().map(aluno -> modelMapper.map(aluno, AlunoResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public AlunoResponseDto consultarPorId(UUID id) {
		
		var aluno = alunoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado. Verifique o ID informado."));
		
		return modelMapper.map(aluno, AlunoResponseDto.class);
	}

}
