package facens.arquiteturaweb.aula3.repository;

import facens.arquiteturaweb.aula3.modelo.Aluno;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

/*
Esta anotação é usada para indicar que a classe é um componente de acesso a dados (repositório).
Ela informa ao Spring que a classe anotada como @Repository é responsável por interagir com a fonte de dados, como um
banco de dados, fornecendo operações CRUD (create, read, update, delete) para uma entidade específica.
 */
@Repository
public class AlunoRepositoryImpl implements AlunoRepository {
    private final List<Aluno> alunos = new ArrayList<>();
    private Long nextId = 1L;

    public AlunoRepositoryImpl() {
        // Adiciona algumas tarefas pré-cadastradas
        alunos.add(new Aluno(1L, "Kauan", "ADS", 20 , "kauan@gmail.com", "celular:119402496"));
        alunos.add(new Aluno(2L, "Kauane", "ADS", 19 , "kauane@gmail.com", "celular:119402497"));
        alunos.add(new Aluno(3L, "Kauano", "ADS", 18 , "kauano@gmail.com", "celular:119402498"));
        nextId = 4L; // Atualiza o próximo ID
    }

    @Override
    public List<Aluno> findAll() {
        return alunos;
    }

    @Override
    public Aluno findById(Long id) {
        return alunos.stream()
                .filter(aluno -> aluno.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Aluno save(Aluno aluno) {
        if (aluno.getId() == null) {
            aluno.setId(nextId++);
            alunos.add(aluno);
        } else {
            alunos.removeIf(t -> t.getId().equals(aluno.getId()));
            alunos.add(aluno);
        }
        return aluno;
    }
}
