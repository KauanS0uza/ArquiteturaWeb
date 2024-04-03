package facens.arquiteturaweb.aula3.service;

import facens.arquiteturaweb.aula3.modelo.Aluno;
import facens.arquiteturaweb.aula3.modelo.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/*
A anotação @Service é usada para marcar uma classe como um componente de serviço no contexto do Spring. Indica
que a classe é uma parte central da lógica de negócios ou executa operações de serviço.
 */
@Service
public class AlunoServiceImpl implements AlunoService {
    private final AlunoRepository alunoRepository;

    /*
     * A injeção de dependência está evidenciada no construtor da classe
     * TaskService. Aqui, TaskService depende de
     * TaskRepository, que é passado como um parâmetro no construtor. O Spring será
     * responsável por injetar uma instância
     * de TaskRepository quando criar uma instância de TaskService.
     */
    public AlunoServiceImpl(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Override
    public List<Aluno> getAllAlunos() {
        return alunoRepository.findAll();
    }

    @Override
    public Aluno getAlunoById(Long id) {
        return alunoRepository.findById(id);
    }

    @Override
    public Aluno createAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @Override
    public Aluno removeAluno(int id) {
        return alunoRepository.delete(id);
    }
}
