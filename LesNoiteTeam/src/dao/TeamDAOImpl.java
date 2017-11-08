package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entidade.Team;

public class TeamDAOImpl implements TeamDAO {
	
	private EntityManagerFactory emf;

	public TeamDAOImpl() { 
		emf = Persistence.createEntityManagerFactory("TIMES");
	}

	@Override
	public void adicionar(Team t) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist( t );
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Team> pesquisar(String nome) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Team> qry = em.createQuery(
				"select t from Team t where t.nome like :n", 
				Team.class);
		qry.setParameter("n", "%" + nome + "%");
		List<Team> lista = qry.getResultList();
		em.close();
		return lista;
	}

}
