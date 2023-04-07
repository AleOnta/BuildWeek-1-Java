package trasportoRunnable;


import java.util.Scanner;
import dao.TransportDAO;
import model.Utente;


public class Runnable {
	
	private static Scanner sc = new Scanner(System.in);
	private static TransportDAO TD = new TransportDAO();
    
	public static void main(String[] args) {
    
		System.out.println("Benvenuto nel gestionale, cosa vuoi fare? \n \n" 
				+ "1 - Gestione della sezione Utenti \n"
				+ "2 - Gestione dei Titoli di Viaggio \n"
				+ "3 - Gestione del Parco mezzi \n \n"
				+ "0 - Per uscire dal gestionale.");	  
		
		int pick = chiediIntero(0, 3);
	
		switch (pick) {
		case 0 -> System.out.println("Chiusura del gestionale");
		// GESTIONE UTENTI
		case 1 -> {
			System.out.println("Benvenuto nella gestione degli utenti, \n"
					+ "1 - Crea un nuovo utente \n"
					+ "2 - Aggiona un utente \n"
					+ "3 - Elimina un utente");
			
			int pickGestioneUtente = chiediIntero(1, 3);
			
			switch (pickGestioneUtente) {
			case 1 -> {
				Utente u = new Utente();
				System.out.println("Inserisci il nome dell'utente:");
				u.setNome( sc.next());
				System.out.println("Inserisci il cognome dell'utente:");
				u.setCognome(sc.next() + sc.nextLine());
				
				
			}
			}
		}
		// GESTIONE TITOLI DI VIAGGIO
		case 2 -> {
			
		}
		// GESTIONE PARCO MEZZI
		case 3 -> {
			
		}
		}
	}

	public static int chiediIntero(int s, int f) {
		boolean isRunning = true;
		int pick = 5;
		while (isRunning) {
			System.out.println("Inserisci tramite tastiera il numero corrispondente all'azione desiderata:");
			pick = sc.nextInt();
			if(pick >= s && pick <= f) {
				break;
			} else {
				System.out.println("Numero non valido.");
				continue;
			}
		}
		return pick;
	}
	
}
