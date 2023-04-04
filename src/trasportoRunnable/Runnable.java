package trasportoRunnable;

import dao.TransportDAO;
import model.RivenditoreFisico;
import model.Utente;
import model.Tessera;
import java.time.LocalDate;
public class Runnable {

	static TransportDAO TD = new TransportDAO();
    public static void main(String[] args) {
    	
    	
		RivenditoreFisico R1 = new RivenditoreFisico();
		R1.setOrarioApertura(8.00);
		R1.setOrarioChiusura(20.00);
		
		TransportDAO.salvaDistributore(R1);
		
		Utente U1 = new Utente("Marcello","DeMarcelli");
		Tessera T1= new Tessera(U1);

		TD.salvaUtente(U1);
		TD.salvaTessera(T1);
    }

}
