package trasportoRunnable;

import dao.TransportDAO;
import model.RivenditoreFisico;
import model.Utente;
import utils.JpaUtil;
import model.Tessera;
import java.time.LocalDate;

import javax.persistence.EntityManager;
public class Runnable {

	static TransportDAO TD = new TransportDAO();
    public static void main(String[] args) {
    	
    	
		RivenditoreFisico R1 = new RivenditoreFisico();
		R1.setOrarioApertura(8.00);
		R1.setOrarioChiusura(20.00);
		
		TransportDAO.salvaDistributore(R1);
		
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		
		em.getTransaction().begin();
		Utente U1 = new Utente("Marcello","DeMarcelli");
		em.persist(U1);
		
		
		
		
		em.getTransaction().commit();
		
		em.refresh(U1);
		for (Tessera t : U1.getTessereUtente()) {
			System.out.println(t.getNumeroTessera());
		}
		em.close();
		
    }

}
