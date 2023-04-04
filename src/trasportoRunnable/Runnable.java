package trasportoRunnable;

import dao.TransportDAO;
import model.RivenditoreFisico;
import model.Utente;
import utils.JpaUtil;
import model.Tessera;
import java.time.LocalDate;
import model.DistributoreAutomatico;
import model.E_distributoreAutomatico;

import javax.persistence.EntityManager;
public class Runnable {

	static TransportDAO TD = new TransportDAO();
    public static void main(String[] args) {
    	
    	
		RivenditoreFisico R1 = new RivenditoreFisico();
		R1.setOrarioApertura(8.00);
		R1.setOrarioChiusura(20.00);
		DistributoreAutomatico D1 = new DistributoreAutomatico();
		D1.setAttivo(E_distributoreAutomatico.ATTIVO);
		D1.setLuogo("ViaRoma");
		
		TransportDAO.salvaDistributore(D1);

		
		/*
		 * EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		 * 
		 * em.getTransaction().begin(); Utente U1 = new Utente("Marcello","DeMarcelli");
		 * em.persist(U1);
		 * 
		 * Tessera T1= new Tessera(); T1.setUtenteProprietario(U1); em.persist(T1);
		 * Tessera T2= new Tessera(); T2.setUtenteProprietario(U1); em.persist(T2);
		 * em.getTransaction().commit();
		 * 
		 * em.refresh(U1); for (Tessera t : U1.getTessereUtente()) {
		 * System.out.println(t.getNumeroTessera()); } em.close();
		 */
		
    }

}
