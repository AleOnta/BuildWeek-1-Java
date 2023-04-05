package trasportoRunnable;

import dao.TransportDAO;
import java.time.LocalTime;
import model.*;

public class Runnable {
	
	private static TransportDAO TD = new TransportDAO();
    
	public static void main(String[] args) {
    
		
		 // Creazione di un utente:
		Utente u1 = new Utente();
		u1.setNome("Utente 1");
		u1.setCognome("DiProva");
		TD.salvaUtente(u1);
		
		// Creazione di una tessera legata all'utente
		Tessera t1 = new Tessera(); // il collegamento della tessera all'utente avviene in TransportDAO
    	TD.salvaTessera(t1, u1);
    	
    	// Creazione di un rivenditore fisico
    	RivenditoreFisico r1 = new RivenditoreFisico();
    	r1.setLuogo("Milano");
    	r1.setOrarioApertura(LocalTime.of(8, 00));
    	r1.setOrarioChiusura(LocalTime.of(20, 00));
    	TD.salvaDistributore(r1);
    	
    	// Creazione di un distributore automatico
    	DistributoreAutomatico d1 = new DistributoreAutomatico();
    	d1.setLuogo("Milano");
    	d1.setAttivo(E_distributoreAutomatico.ATTIVO);
    	TD.salvaDistributore(d1);
    	
    	// Acquisto di un biglietto da parte di un utente
    	Biglietto b1 = new Biglietto();
    	b1.setEmittente(d1);
    	TD.salvaBiglietto(b1, u1); // il collegamento del biglietto all'utente avviene in TransportDAO
    	
    	// Creazione di un abbonamento passando una tessera
    	Abbonamento a1 = r1.vendiAbbonamento(t1, E_Abbonamento.MENSILE);
    	TD.salvaAbbonamento(a1, t1);
    	
    	
    	/*List<Titolo_di_Viaggio> lT = TD.findEmessiInData(LocalDate.of(2023, 01, 20), LocalDate.of(2023, 03, 25));
    	for (Titolo_di_Viaggio t : lT) {
    		System.out.println(t);
    	}*/
    	
    	
	}

}
