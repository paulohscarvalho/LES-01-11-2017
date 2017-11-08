package dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidade.Team;

public class TesteDB {

	public static void main(String[] args) {
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("TIMES");
		EntityManager em = emf.createEntityManager();
		
		Team t1 = new Team();
		t1.setNome("Corinthians");
		t1.setEstadio("Arena");
		t1.setDataFundacao(new Date());
		t1.setMembros(1000000);
		
		em.getTransaction().begin();
		em.persist( t1 );
		em.getTransaction().commit();
		em.close();
		
		emf.close();
	}

}
