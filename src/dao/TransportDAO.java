package dao;

import java.time.LocalDate;
import java.util.ArrayList;
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
import model_parco_mezzi.E_StatusVeicolo;
import model_parco_mezzi.Manutenzione;
import model_parco_mezzi.Tratta;
import model_parco_mezzi.Veicolo;
import utils.JpaUtil;

public class TransportDAO implements I_metodi{
	public TransportDAO() {
	}
	
	// Metodi relativi al persist di entità
	
	public void salvaEntita(VenditaBiglietto r) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.persist(r);
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
	
	public void salvaEntita(Utente u) {
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
	
	public void salvaEntita(Tessera t, Utente u) {
		t.setUtenteProprietario(u);
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
			System.out.println("Tessera creata correttamente");
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("Errore nella creazione della tessera");
		} finally {
			em.close();
		}
		
	}
	
	public void salvaEntita(Biglietto b, Utente u) {
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
	
	public void salvaEntita(Abbonamento a, Tessera t) {
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
	
	public void salvaEntita(Veicolo v) {
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
	
	public void salvaEntita(Veicolo v, Manutenzione m) {
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
	
	public void salvaEntita(Convalida c) {
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
		
	public void salvaEntita(Tratta tr, Veicolo v) {
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
		
	public void salvaEntita(Viaggio route, Veicolo v) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(v);
			em.persist(route);
			em.getTransaction().commit();
			System.out.println("Viaggio inserito correttamente");
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			System.out.println("Errore nell'inserimento del viaggio");
		} finally {
			em.close();
		}
	}
	
	// Metodi relativi all'aggiornamento dell'entità
	
	public void aggiornaEntita(VenditaBiglietto r) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.merge(r);
			transaction.commit();
			System.out.println("Aggiornamento dell'emittente eseguita correttamente");
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("Errore nell'aggiornamento dell'emittente");
		} finally {
			em.close();
		}
	}
	
	public void aggiornaEntita(Utente u) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.merge(u);
			transaction.commit();
			System.out.println("Utente aggiornato correttamente");
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("Errore nell'aggiornamento dell'utente");
		} finally {
			em.close();
		}
		
	}
	
	public void aggiornaEntita(Tessera t) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(t);
			em.getTransaction().commit();
			System.out.println("Tessera aggiornata correttamente");
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("Errore nell'aggiornamento della tessera");
		} finally {
			em.close();
		}
		
	}
	
	public void aggiornaEntita(Biglietto b) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(b);
			em.getTransaction().commit();
			System.out.println("Biglietto aggiornato correttamente");
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			System.out.println("Errore nell'aggiornamento del biglietto");
		} finally {
			em.close();
		}
	}
	
	public void aggiornaEntita(Abbonamento a) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(a);
			em.getTransaction().commit();
			System.out.println("Abbonamento aggiornato correttamente");
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			System.out.println("Errore nell'aggiornamento dell'abbonamento");
		} finally {
			em.close();
		}
	}
	
	public void aggiornaEntita(Veicolo v) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(v);
			em.getTransaction().commit();
			System.out.println("Veicolo aggiornato correttamente");
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			System.out.println("Errore nell'aggiornamento del veicolo");
		} finally {
			em.close();
		}
	}
	
	public void aggiornaEntita(Manutenzione m) {
		Veicolo v = m.getId_veicolo();
		v.checkStatus(m);
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(m);
			em.merge(v);
			em.getTransaction().commit();
			System.out.println("Manutenzione veicolo aggiornata correttamente");
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			System.out.println("Errore nell'aggiornamento della manutenzione del veicolo");
		} finally {
			em.close();
		}
	}
	
	public void aggiornaEntita(Convalida c) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(c);
			em.getTransaction().commit();
			System.out.println("Convalida biglietto aggiornata correttamente");
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			System.out.println("Errore nell'aggiornamento della convalida del biglietto");
		} finally {
			em.close();
		}
	}
		
	public void aggiornaEntita(Tratta tr) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(tr);
			em.getTransaction().commit();
			System.out.println("Tratta aggiornata correttamente");
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			System.out.println("Errore nell'aggiornamento della tratta");
		} finally {
			em.close();
		}
	}
		
	public void aggiornaEntita(Viaggio v) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(v);
			em.getTransaction().commit();
			System.out.println("Viaggio aggiornato correttamente");
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			System.out.println("Errore nell'aggiornamento del viaggio");
		} finally {
			em.close();
		}
	}
	
	// Metodi relativi alla ricerca di entità
	
	public VenditaBiglietto findEmittente(Long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		VenditaBiglietto entitaTrovata = null;
		try {
			em.getTransaction().begin();
			entitaTrovata = em.find(VenditaBiglietto.class, id);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return entitaTrovata;
	}
	
	public Utente findUtente(Long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Utente entitaTrovata = null;
		try {
			em.getTransaction().begin();
			entitaTrovata = em.find(Utente.class, id);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return entitaTrovata;
	}
	
	public Tessera findTessera(Long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Tessera entitaTrovata = null;
		try {
			em.getTransaction().begin();
			entitaTrovata = em.find(Tessera.class, id);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return entitaTrovata;
	}
	
	public Biglietto findBiglietto(Long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Biglietto entitaTrovata = null;
		try {
			em.getTransaction().begin();
			entitaTrovata = em.find(Biglietto.class, id);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return entitaTrovata;
	}
	
	public Abbonamento findAbbonamento(Long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Abbonamento entitaTrovata = null;
		try {
			em.getTransaction().begin();
			entitaTrovata = em.find(Abbonamento.class, id);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return entitaTrovata;
	}
	
	public Veicolo findVeicolo(Long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Veicolo entitaTrovata = null;
		try {
			em.getTransaction().begin();
			entitaTrovata = em.find(Veicolo.class, id);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return entitaTrovata;
	}
	
	public Manutenzione findManutenzione(Long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Manutenzione entitaTrovata = null;
		try {
			em.getTransaction().begin();
			entitaTrovata = em.find(Manutenzione.class, id);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return entitaTrovata;
	}
	
	public Convalida findConvalida(Long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Convalida entitaTrovata = null;
		try {
			em.getTransaction().begin();
			entitaTrovata = em.find(Convalida.class, id);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return entitaTrovata;
	}
	
	public Tratta findTratta(Long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Tratta entitaTrovata = null;
		try {
			em.getTransaction().begin();
			entitaTrovata = em.find(Tratta.class, id);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return entitaTrovata;
	}
	
	public Viaggio findViaggio(Long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Viaggio entitaTrovata = null;
		try {
			em.getTransaction().begin();
			entitaTrovata = em.find(Viaggio.class, id);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return entitaTrovata;
	}
	
	// Metodi relativi all'eliminazione delle entità
	
	public void eliminaEntita(VenditaBiglietto r) {
		
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			VenditaBiglietto r2 = em.find(VenditaBiglietto.class, r.getId_rivenditore());
			em.remove(r2);
			transaction.commit();
			System.out.println("Eliminazione dell'emittente eseguita correttamente");
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("Errore nella eliminazione dell'emittente");
		} finally {
			em.close();
		}
	}

	public void eliminaEntita(Utente u) {
		
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			Utente u2 = em.find(Utente.class, u.getId());
			em.remove(u2);
			transaction.commit();
			System.out.println("Utente eliminato correttamente");
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("Errore nell'eliminazione dell'utente");
		} finally {
			em.close();
		}
	}
	
	public void eliminaEntita(Tessera t) {
		
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Tessera t2 = em.find(Tessera.class, t.getNumeroTessera());
			em.remove(t2);
			em.getTransaction().commit();
			System.out.println("Tessera eliminata correttamente");
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("Errore nell'eliminazione della tessera");
		} finally {
			em.close();
		}
	}
	
	public void eliminaEntita(Biglietto b) {

		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Biglietto b2 = em.find(Biglietto.class, b.getId());
			em.remove(b2);
			em.getTransaction().commit();
			System.out.println("Biglietto eliminato correttamente");
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			System.out.println("Errore nell'eliminazione del biglietto");
		} finally {
			em.close();
		}
	}
	
	public void eliminaEntita(Abbonamento a) {
		
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Abbonamento a2 = em.find(Abbonamento.class, a.getId());
			em.remove(a2);
			em.getTransaction().commit();
			System.out.println("Abbonamento eliminato correttamente");
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			System.out.println("Errore nell'eliminazione dell'abbonamento");
		} finally {
			em.close();
		}
	}
	
	public void eliminaEntita(Veicolo v) {
		
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Veicolo v2 = em.find(Veicolo.class, v.getId_veicolo());
			em.remove(v2);
			em.getTransaction().commit();
			System.out.println("Veicolo aggiornato correttamente");
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			System.out.println("Errore nell'aggiornamento del veicolo");
		} finally {
			em.close();
		}
	}
	
	public void eliminaEntita(Manutenzione m) {
		
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Veicolo v = m.getId_veicolo();
			v.setStatus(E_StatusVeicolo.IN_SERVIZIO);
			em.merge(v);
			Manutenzione m2 = em.find(Manutenzione.class, m.getId());
			em.remove(m2);
			em.getTransaction().commit();
			System.out.println("Manutenzione veicolo eliminata correttamente");
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			System.out.println("Errore nell'eliminazione della manutenzione del veicolo");
		} finally {
			em.close();
		}
	}
	
	public void eliminaEntita(Convalida c) {
		
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Convalida c2 = em.find(Convalida.class, c.getId_convalida());
			em.remove(c2);
			em.getTransaction().commit();
			System.out.println("Convalida biglietto eliminata correttamente");
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			System.out.println("Errore nell'eliminazione della convalida del biglietto");
		} finally {
			em.close();
		}
	}
		
	public void eliminaEntita(Tratta tr) {

		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Tratta tr2 = em.find(Tratta.class, tr.getId());
			em.remove(tr2);
			em.getTransaction().commit();
			System.out.println("Tratta eliminata correttamente");
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			System.out.println("Errore nell'eliminazione della tratta");
		} finally {
			em.close();
		}
	}
		
	public void eliminaEmittente(Viaggio v) {

		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Viaggio v2 = em.find(Viaggio.class, v.getId());
			em.remove(v2);
			em.getTransaction().commit();
			System.out.println("Viaggio eliminato correttamente");
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			System.out.println("Errore nell'eliminazione del viaggio");
		} finally {
			em.close();
		}
	}

	// Metodi custom JPQL 
	
	@SuppressWarnings("unchecked")
	public List<Biglietto> trovaBigliettiUtente(Long id_utente) {
		Utente utente = findUtente(id_utente);
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		List<Biglietto> resList = new ArrayList<Biglietto>();
		try { 
			Query query = em.createNamedQuery("Biglietto.findByIdUtente");
			query.setParameter("id", utente.getId());
			resList = query.getResultList();
		} catch (Exception e) {
			System.out.println("Errore nella ricerca dei biglietti tramite utente");
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return resList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Biglietto> trovaBigliettiUtente2(Utente u) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Query q = em.createNamedQuery("utenti.findBigliettiUtente");
		q.setParameter("param", u);
		return q.getResultList();
	}
	
	
	public static Abbonamento findAbbonamentoTramiteTessera(Tessera i) {
		
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
	public static List<Convalida> findConvalidaTramiteVeicolo(Veicolo v) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try { 
			Query querySelect = em.createQuery("SELECT c FROM Convalida c WHERE c.convalidato_su =" + v.getId_veicolo());
			@SuppressWarnings("unchecked")
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
		Query q = em.createNamedQuery("utenti.findBiglietti");
		return q.getResultList();
	}


	
	@SuppressWarnings("unchecked")
	public List<Tessera> trovaTutteTessere(int id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Query q = em.createNamedQuery("utenti.findTessereUtente");
		q.setParameter("id", (long)id);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Titolo_di_Viaggio> findEmessiInData(LocalDate dataUno, LocalDate dataDue) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Query q = em.createNamedQuery("Titolo.emessiInData");
		q.setParameter("data1", dataUno);
		q.setParameter("data2", dataDue);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Biglietto> findBigliettiEmessiInData(LocalDate dataUno, LocalDate dataDue) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Query q = em.createNamedQuery("Biglietto.emessiInData");
		q.setParameter("data1", dataUno);
		q.setParameter("data2", dataDue);
		return q.getResultList();
	}

	// METODI PER LA GESTIONE DEL PARCO MEZZI
		
	@SuppressWarnings("unchecked")
	public List<Convalida> findConvalidatiInData(LocalDate dataUno, LocalDate dataDue) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Query q = em.createNamedQuery("Convalide.emesseInData");
		q.setParameter("data1", dataUno);
		q.setParameter("data2", dataDue);
		return q.getResultList();
	}
		
	@SuppressWarnings("unchecked")
	public List<Veicolo> trovaTuttiIVeicoli() {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Query q = em.createNamedQuery("Veicoli.CercaTuttiIVeicoli");
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Utente> trovaTuttiUtenti() {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Query q = em.createNamedQuery("utenti.getAll");
		return q.getResultList();
	}
	
	public List<VenditaBiglietto> trovaTuttiRivenditori() {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Query q = em.createNamedQuery("rivenditori.findAll");
		return q.getResultList();
	}
		
		
		

		
		

}
