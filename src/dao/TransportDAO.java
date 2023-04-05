package dao;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import model.Abbonamento;
import model.Biglietto;
import model.Tessera;
import model.Titolo_di_Viaggio;
import model.Utente;
import model.VenditaBiglietto;
import utils.JpaUtil;

public class TransportDAO implements I_metodi{
	public TransportDAO() {
	}
	
	// Metodi relativi alla vendita dei biglietti / abbonamenti
	public void salvaDistributore(VenditaBiglietto R) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.persist(R);
			transaction.commit();
			System.out.println("Creazione dell'emittente eseguita correttamente");
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("Errore nella creazione dell'emittente");
		} finally {
			em.close();
		}
	}


	public void salvaUtente(Utente u) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.persist(u);
			transaction.commit();
			System.out.println("Utente creato correttamente");
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("Errore nella creazione dell'utente");
		} finally {
			em.close();
		}
		
	}
	
	public void salvaTessera(Tessera t, Utente u) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			t.setUtenteProprietario(u);
			em.persist(t);
			transaction.commit();
			System.out.println("Tessera creata correttamente");
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("Errore nella creazione della tessera");
		} finally {
			em.close();
		}
		
	}

	public void salvaBiglietto(Biglietto b, Utente u) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			b.setUtente(u);
			em.persist(b);
			em.getTransaction().commit();
			System.out.println("Biglietto acquistato correttamente");
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			System.out.println("Errore nell'acquisto del biglietto");
		} finally {
			em.close();
		}
	}
	
	public void salvaAbbonamento(Abbonamento a, Tessera t) {
		if (a == null) {
			System.out.println("Crea un'istanza tessera e salvala tramite salvaTessera() prima di sottoscrivere un'abbonamento");
		} else {
			EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
			try {
				em.getTransaction().begin();
				a.setTessera_proprietario(t);
				em.persist(a);
				em.getTransaction().commit();
				System.out.println("Abbonamento sottoscritto correttamente");
			} catch (Exception e) {
				e.printStackTrace();
				em.getTransaction().rollback();
				System.out.println("Errore nella sottoscrizione dell'abbonamento");
			} finally {
				em.close();
			}
		}
	}
	
	public void findEntity() {
	}
	
	public Utente findEntity(long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Utente utenteTrovato = null;
		try {
			em.getTransaction().begin();
			utenteTrovato = em.find(Utente.class, id);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return utenteTrovato;
	}
	
	@SuppressWarnings("unchecked")
	public List<Biglietto> trovaTuttiBiglietti() {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Query q = em.createNamedQuery("utenti.findBigliettiUtente");
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Tessera> trovaTutteTessere(int id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Query q = em.createNamedQuery("utenti.findTessereUtente");
		q.setParameter("id", (long)id);
		return q.getResultList();
	}
	
	public List<Titolo_di_Viaggio> findEmessiInData(LocalDate dataUno, LocalDate dataDue) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Query q = em.createNamedQuery("Titolo.emessiInData");
		q.setParameter("data1", dataUno);
		q.setParameter("data2", dataDue);
		return q.getResultList();
	}

}
