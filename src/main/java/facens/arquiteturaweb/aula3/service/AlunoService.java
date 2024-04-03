package facens.arquiteturaweb.aula3.service;

import facens.arquiteturaweb.aula3.modelo.Aluno;

import java.util.List;

public interface AlunoService {
    List<Aluno> getAllAlunos();

    Aluno getAlunoById(Long id);

    Aluno createAluno(Aluno aluno);
}
