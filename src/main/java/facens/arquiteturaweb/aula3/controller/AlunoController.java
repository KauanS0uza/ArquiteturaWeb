package facens.arquiteturaweb.aula3.controller;

import facens.arquiteturaweb.aula3.modelo.Aluno;
import facens.arquiteturaweb.aula3.service.AlunoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    private final AlunoService alunoService;

    /*
     * a injeção de dependência é visível no construtor da classe TaskController.
     * TaskController depende de TaskService,
     * que é passado como um parâmetro no construtor. O Spring será responsável por
     * injetar uma instância de TaskService
     * quando criar uma instância de TaskController.
     */
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public List<Aluno> getAllAlunos() {
        return alunoService.getAllAlunos();
    }

    @GetMapping("/{id}")
    public Aluno getAlunoById(@PathVariable Long id) {
        return alunoService.getAlunoById(id);
    }

    @PostMapping("/add")
    public Aluno createAluno(@RequestBody Aluno aluno) {
        return alunoService.createAluno(aluno);
    }

    // Na configuração do endepoint é necessário adicionar entre chaves aonde ficará
    // na url a variavel configurada abaixo
    @DeleteMapping("/remove/{id}")
    // A notação PathVariable permite que a API receba informações que estão
    // contidas na URL
    public Aluno removeAluno(@PathVariable int id) {
        return alunoService.removeAluno(id);
    }
}
