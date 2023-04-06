package dao;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import model.Abbonamento;
import model.Biglietto;
import model.Convalida;
import model.Tessera;
import model.Titolo_di_Viaggio;
import model.Utente;
import model.VenditaBiglietto;
import model.Viaggio;
import model_parco_mezzi.Manutenzione;
import model_parco_mezzi.Tratta;
import model_parco_mezzi.Veicolo;
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
		b.setUtente(u);
		b.setValido(true);
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
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
	public static Abbonamento findAbbonamento(Tessera i) {
		
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try { 
			Query querySelect = em.createQuery("SELECT a FROM Abbonamento a WHERE a.id_tessera =" + i.getNumeroTessera());
			Abbonamento resList =  (Abbonamento) querySelect.getResultList();
			System.out.println(resList);
			return resList;
		} catch (Exception ex) {
			em.getTransaction().rollback();
			System.out.println("Errore nel cercare l`abbonamento");
			throw ex;
		} finally {
			em.close();
		}
	}
	public static List<Convalida> findConvalida(Veicolo v) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try { 
			Query querySelect = em.createQuery("SELECT c FROM Convalida c WHERE c.convalidato_su =" + v.getId_veicolo());
			List<Convalida> resList = querySelect.getResultList();
			return resList;
		} catch (Exception ex) {
			em.getTransaction().rollback();
			System.out.println("Errore nel cercare il biglietto");
			throw ex;
		} finally {
			em.close();
		}
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

	// METODI PER LA GESTIONE DEL PARCO MEZZI
	
	public void salvaMezzo(Veicolo v) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(v);
			em.getTransaction().commit();
			System.out.println("Veicolo inserito correttamente");
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			System.out.println("Errore nell'inserimento del veicolo");
		} finally {
			em.close();
		}
	}
	
	public void salvaManutenzione(Veicolo v, Manutenzione m) {
		m.setId_veicolo(v);
		v.checkStatus(m);
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(m);
			em.merge(v);
			em.getTransaction().commit();
			System.out.println("Manutenzione veicolo inserita correttamente");
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			System.out.println("Errore nell'inserimento della manutenzione del veicolo");
		} finally {
			em.close();
		}
	}
	
		public void salvaConvalida(Convalida c) {
			if (c.getBiglietto().isValido()) {
				Biglietto b = c.getBiglietto();
			b.setValido(false);
			EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
			try {
				em.getTransaction().begin();
				em.persist(c);
				em.merge(b);
				em.getTransaction().commit();
				System.out.println("Convalida biglietto effetuata correttamente");
			} catch (Exception e) {
				e.printStackTrace();
				em.getTransaction().rollback();
				System.out.println("Errore nella convalida del biglietto");
			} finally {
				em.close();
			}
			} else {
				System.out.println("Biglietto già convalidato, rivolgiti ad un rivenditore per acquistarne un nuovo");
			}
		}
		public List<Convalida> findConvalidatiInData(LocalDate dataUno, LocalDate dataDue) {
			EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
			Query q = em.createNamedQuery("Convalide.emesseInData");
			q.setParameter("data1", dataUno);
			q.setParameter("data2", dataDue);
			return q.getResultList();
		}
		
		public void salvaTratta(Tratta tr, Veicolo v) {
			v.setTappa_assegnata(tr);
			EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
			try {
				em.getTransaction().begin();
				em.merge(v);
				em.persist(tr);
				em.getTransaction().commit();
				System.out.println("Tratta inserita correttamente");
			} catch (Exception e) {
				e.printStackTrace();
				em.getTransaction().rollback();
				System.out.println("Errore nell'inserimento della tratta");
			} finally {
				em.close();
			}
		}
		
		@SuppressWarnings("unchecked")
		public List<Veicolo> trovaTuttiIVeicoli() {
			EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
			Query q = em.createNamedQuery("Veicoli.CercaTuttiIVeicoli");
			return q.getResultList();
		}
		
		
		public void salvaViaggio(Viaggio route, Veicolo v) {
			EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
			try {
				em.getTransaction().begin();
				em.merge(v);
				em.persist(route);
				em.getTransaction().commit();
				System.out.println("Tratta inserita correttamente");
			} catch (Exception e) {
				e.printStackTrace();
				em.getTransaction().rollback();
				System.out.println("Errore nell'inserimento della tratta");
			} finally {
				em.close();
			}
		}

		
		

}
