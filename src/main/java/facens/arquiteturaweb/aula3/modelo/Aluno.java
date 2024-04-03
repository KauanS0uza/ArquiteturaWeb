package facens.arquiteturaweb.aula3.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "aluno")
public class Aluno {
    @Id
    private Long id;
    private String nome;
    private String curso;
    private int idade;
    private String email;
    private String contato;

    public Aluno(Long id, String nome, String curso, int idade, String email, String contato) {
        this.id = id;
        this.nome = nome;
        this.curso = curso;
        this.idade = idade;
        this.email = email;
        this.contato = contato;
    }

    public Aluno() {
    }

    // Getters e Setters (exemplo)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}