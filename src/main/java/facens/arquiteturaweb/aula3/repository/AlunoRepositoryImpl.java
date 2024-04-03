package facens.arquiteturaweb.aula3.repository;

import facens.arquiteturaweb.aula3.modelo.Aluno;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

/*
Esta anotação é usada para indicar que a classe é um componente de acesso a dados (repositório).
Ela informa ao Spring que a classe anotada como @Repository é responsável por interagir com a fonte de dados, como um
banco de dados, fornecendo operações CRUD (create, read, update, delete) para uma entidade específica.
 */
@Repository
public class AlunoRepositoryImpl implements AlunoRepository {

    /*
     * O JdbcTemplate é uma classe oferecida pelo Spring Framework para simplificar
     * o uso do JDBC (Java Database Connectivity)
     * e interagir com bancos de dados relacionais de uma maneira mais fácil e
     * eficiente. Ele fornece métodos convenientes
     * para executar operações comuns do JDBC, como consultas, atualizações,
     * inserções e exclusões de dados no banco de dados.
     */
    private final JdbcTemplate jdbcTemplate;

    /*
     * A injeção acontecendo no contrutor
     */
    public AlunoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Aluno> findAll() {
        /*
         * Este método executa uma consulta SQL no banco de dados usando a instrução
         * SELECT * FROM task. Ele espera uma função
         * lambda (ou expressão lambda) como segundo argumento para mapear os
         * resultados.
         */
        return jdbcTemplate.query("SELECT * FROM aluno", (resultSet, rowNum) -> {
            System.out.println("Numero da linha: " + rowNum);
            return new Aluno(
                    resultSet.getLong("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("curso"),
                    resultSet.getInt("idade"),
                    resultSet.getString("email"),
                    resultSet.getString("contato"));
        });
    }

    @SuppressWarnings("deprecation")
    @Override
    public Aluno findById(Long id) {
        String query = "SELECT * FROM aluno WHERE id = ?";
        /*
         * Este método é usado para executar uma consulta SQL que retorna um único
         * resultado. Ele espera a consulta SQL,
         * um array de parâmetros de consulta (nesse caso, o ID) e uma função lambda que
         * mapeia o resultado do banco de dados
         * para um objeto Java.
         * 
         * A quantidade e ordem de atributos no array de parametros deve ser a mesma na
         * consulta SQL.
         */
        return jdbcTemplate.queryForObject(query, new Object[] { id }, (resultSet, rowNum) -> new Aluno(
                /*
                 * Para se recuperar os valores das colunas é preciso saber o tipo e o nome,
                 * pois os métodos são
                 * especificos
                 */
                resultSet.getLong("id"),
                resultSet.getString("nome"),
                resultSet.getString("curso"),
                resultSet.getInt("idade"),
                resultSet.getString("email"),
                resultSet.getString("contato")));
    }

    @Override
    public Aluno save(Aluno aluno) {
        if (aluno.getId() != null) {
            String insertQuery = "INSERT INTO public.aluno (id, nome, curso, idade, email, contato) VALUES (?, ?, ?, ?, ?, ?)";
            /*
             * utilizada para executar operações de atualização no banco de dados usando o
             * Spring JDBC. Essa função é
             * comumente usada para operações que modificam os dados no banco, como
             * inserções, atualizações ou exclusões.
             */
            jdbcTemplate.update(insertQuery, aluno.getId(), aluno.getNome(), aluno.getCurso(), aluno.getIdade(),
                    aluno.getEmail(), aluno.getContato());
        } else {
            String updateQuery = "UPDATE public.task SET title = ?, description = ? WHERE id = ?";
            jdbcTemplate.update(updateQuery, aluno.getNome(), aluno.getCurso(), aluno.getIdade(), aluno.getEmail(),
                    aluno.getContato(), aluno.getId());
        }
        return aluno;
    }
}
