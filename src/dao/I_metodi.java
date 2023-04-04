package dao;


import model.Tessera;
import model.Utente;
import model.VenditaBiglietto;

public interface I_metodi {
	//ridistributori biglietti 
	public void salvaDistributore(VenditaBiglietto R); 
	
	// biglietti e abbonamenti
	//public void salvaBiglietto(TitoloDiViaggio B);
	
	//users e tessere
	public void salvaUtente(Utente u);
	
	public void salvaTessera(Tessera T);
	
	//macchine e routes
	
}
