package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Tessera;
import model.Utente;
import model.VenditaBiglietto;
import utils.JpaUtil;

public class TransportDAO implements I_metodi{
	public TransportDAO() {
	}
	public static void salvaDistributore(VenditaBiglietto R) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
	
			em.persist(R);
	
			transaction.commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
	
		 System.out.println("Error saving object: " + R.getClass().getSimpleName());
		 System.out.println(ex);
			throw ex;
		}
	}


	public static void salvaUtente(Utente u) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
	
			em.persist(u);
	
			transaction.commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
	
		 System.out.println("Error saving object: " + u.getClass().getSimpleName());
		 System.out.println(ex);
			throw ex;
		}
		
	}

	public void salvaTessera(Tessera T) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
	
			em.persist(T);
	
			transaction.commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
	
		 System.out.println("Error saving object: " + T.getClass().getSimpleName());
		 System.out.println(ex);
			throw ex;
		}
		
	}

}
