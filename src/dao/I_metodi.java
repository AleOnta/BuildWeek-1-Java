package dao;


import model.Tessera;
import model.Utente;
import model.VenditaBiglietto;

public interface I_metodi {
	//ridistributori biglietti 
	public static void salvaDistributore(VenditaBiglietto R) {
	} 
	
	// biglietti e abbonamenti
	//public void salvaBiglietto(TitoloDiViaggio B);
	
	//users e tessere
	public static void salvaUtente(Utente u) {};
	
	public static void salvaTessera(Tessera T) {};
	
	//macchine e routes
	
}
