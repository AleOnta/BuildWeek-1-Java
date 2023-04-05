package trasportoRunnable;

import dao.TransportDAO;
import java.util.Scanner;
import utils.JpaUtil;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.EntityManager;
import model.*;

public class Runnable {
	public static Scanner sc = new Scanner(System.in);
	static TransportDAO TD = new TransportDAO();
    public static void main(String[] args) {
    	
    	
		RivenditoreFisico R1 = new RivenditoreFisico();
		R1.setOrarioApertura(LocalTime.of(06, 00));
		R1.setOrarioChiusura(LocalTime.of(20, 00));
		R1.setLuogo("Milano");
		//DistributoreAutomatico D1 = new DistributoreAutomatico();
		//D1.setAttivo(E_distributoreAutomatico.ATTIVO);
		//D1.setLuogo("ViaRoma");
		
		TransportDAO.salvaDistributore(R1);
		
    	
    	EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
    	try {
    		em.getTransaction().begin();
    		
    		Utente u1 = new Utente();
	    	u1.setNome("Ale");
	    	u1.setCognome("GIGA");
        	em.persist(u1);
    		
    		Biglietto b1 = new Biglietto();
        	b1.setEmittente(R1);
        	b1.setUtente(u1);
    		em.persist(b1);
    		
    		Biglietto b2 = new Biglietto();
        	b2.setEmittente(R1);
        	b2.setUtente(u1);
    		em.persist(b2);
    		
    		Biglietto b3 = new Biglietto();
        	b3.setEmittente(R1);
        	b3.setUtilizzo(LocalDate.of(2023, 02, 18));
        	b3.setUtente(u1);
        	em.persist(b3);
        	
        	// Creo la tessera e faccio upload
        	Tessera t1 = new Tessera();
        	t1.setUtenteProprietario(u1);
        	em.persist(t1);
        	em.refresh(u1); // refresh post tessera
        	
        	// Creo due abbonamenti e faccio upload legato a tessera
        	Abbonamento a1 = R1.vendiAbbonamento(u1, E_Abbonamento.MENSILE);
        	em.persist(a1);
    		Abbonamento a2 = R1.vendiAbbonamento(u1, E_Abbonamento.SETTIMANALE);
        	em.persist(a2);
        	
        	em.refresh(t1); // refresh tessera post abbonamenti
        	em.refresh(u1); // refresh tessera post abbonamenti
        	
        	// chiudo la transaction
    		em.getTransaction().commit();
    		em.refresh(u1);
    		t1.getAbbonamenti();
    	} catch (Exception e) {
    		em.getTransaction().rollback();
    	} finally {
    		
    		em.close();
    	}
    
    	EntityManager em2 = JpaUtil.getEntityManagerFactory().createEntityManager();
    	Tessera t1 = null;
    	try {
    		em2.getTransaction().begin();
    		
    		t1 = em2.find(Tessera.class, 1);
    		
    		em2.getTransaction().commit();    		
    	} catch (Exception e) {
    		em2.getTransaction().rollback();
    	} finally {
    		
    		em2.close();
    	}
    	
    	
		
		/*
		 * EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		 * 
		 * em.getTransaction().begin(); 
		 * Utente U1 = new Utente("Marcello","DeMarcelli");
		 * em.persist(U1);
		 * 
		 * Tessera T1= new Tessera(); 
		 * T1.setUtenteProprietario(U1); 
		 * em.persist(T1);
		 * Tessera T2= new Tessera(); 
		 * T2.setUtenteProprietario(U1); 
		 * em.persist(T2);
		 * em.getTransaction().commit();
		 * 
		 * em.refresh(U1); for (Tessera t : U1.getTessereUtente()) {
		 * System.out.println(t.getNumeroTessera()); } em.close();
		 */
		
    }

}
