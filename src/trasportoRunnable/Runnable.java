package trasportoRunnable;

import model_parco_mezzi.*;
import dao.TransportDAO;
import java.util.List;
import java.time.LocalDate;
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
    	
    	Biglietto b2 = new Biglietto();
    	b2.setEmittente(d1);
    	TD.salvaBiglietto(b2, u1); // il collegamento del biglietto all'utente avviene in TransportDAO
    	
    	Biglietto b3 = new Biglietto();
    	b3.setEmittente(d1);
    	TD.salvaBiglietto(b3, u1);
    	
    	// Creazione di un abbonamento passando una tessera
    	Abbonamento a1 = r1.vendiAbbonamento(t1, E_Abbonamento.MENSILE);
    	TD.salvaAbbonamento(a1, t1);
    	
		
		Veicolo v1 = new Veicolo();
		Veicolo v2 = new Veicolo(E_Veicolo.TRAM);
		Manutenzione m1 = new Manutenzione();
		m1.setInizio(LocalDate.of(2021, 8, 15));
		m1.setFine(LocalDate.of(2021, 12, 20));
		Manutenzione m2 = new Manutenzione();
		m2.setInizio(LocalDate.of(2023, 2, 10));
		m2.setFine(LocalDate.of(2023, 11, 05));
		Manutenzione m3 = new Manutenzione();
		m3.setInizio(LocalDate.of(2022, 5, 10));
		m3.setFine(LocalDate.of(2022, 11, 30));
		
		TD.salvaMezzo(v1);
		TD.salvaMezzo(v2);
		TD.salvaManutenzione(v1, m1);
		TD.salvaManutenzione(v1, m2);
		TD.salvaManutenzione(v2, m3);
		
		
		
		Convalida c1 = new Convalida(b1, v1);
		TD.salvaConvalida(c1);
		
		Convalida c2 = new Convalida (b2, v1); 
		TD.salvaConvalida(c2);
		
		Convalida c3 = new Convalida (b3,v1);
		c3.setData_convalida(LocalDate.of(2023, 10, 10));
		TD.salvaConvalida(c3);
		
		
		//System.out.println(TD.findConvalida(v1));
		System.out.println(TD.findConvalidatiInData(LocalDate.of(2023, 4, 1),LocalDate.of(2023, 5, 30)));
    	
    	/*List<Titolo_di_Viaggio> lT = TD.findEmessiInData(LocalDate.of(2023, 01, 20), LocalDate.of(2023, 03, 25));
    	for (Titolo_di_Viaggio t : lT) {
    		System.out.println(t);
    	}*/
    	
    	
	}

}
