package facens.arquiteturaweb.aula3.modelo;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import facens.arquiteturaweb.aula3.config.HibernateConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlunoRepositoryImpl implements AlunoRepository {

    /*
     * É uma instância final da SessionFactory do Hibernate, que será utilizada
     * para criar e gerenciar sessões com o banco de dados.
     */
    private final SessionFactory sessionFactory;

    public AlunoRepositoryImpl() {
        this.sessionFactory = HibernateConfig.getSessionFactory();
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Aluno> findAll() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        /*
         * Cria uma query HQL para buscar todas as instâncias da entidade Task no banco
         * de dados e retorna uma lista de tarefas.
         */
        List<Aluno> alunos = session.createQuery("FROM Aluno", Aluno.class).getResultList();
        // Confirma a transação, aplicando as operações realizadas na sessão.
        transaction.commit();
        return alunos;
    }

    @Override
    public Aluno findById(Long id) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Aluno aluno = session.find(Aluno.class, id);
        transaction.commit();
        return aluno;
    }

    @Override
    public Aluno save(Aluno aluno) {
        // Session session = getSession();
        // Transaction transaction = session.beginTransaction();
        // session.persist(task);
        // transaction.commit();
        // return task;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(aluno);
        transaction.commit();
        return aluno;
    }

    @Override
    public Aluno delete(int id) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        // Consulta HQL para deletar a Task pelo ID
        String hqlQuery = "DELETE FROM Aluno WHERE id = :alunoId";
        @SuppressWarnings("deprecation")
        Query query = session.createQuery(hqlQuery);
        query.setParameter("alunoId", id);

        int deletedCount = query.executeUpdate(); // Executa a operação de exclusão e retorna a quantidade de registros
                                                  // deletados
        transaction.commit();

        if (deletedCount > 0) {
            // Se algum registro foi deletado, você pode retornar uma instância vazia de
            // Task ou uma mensagem de confirmação
            return new Aluno(); // ou null, dependendo do comportamento desejado
        } else {
            // Se nenhum registro foi deletado, pode-se lançar uma exceção ou retornar null,
            // dependendo do tratamento esperado
            return null;
        }
    }
}
