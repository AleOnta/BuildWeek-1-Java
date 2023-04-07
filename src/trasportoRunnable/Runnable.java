package trasportoRunnable;


import java.util.List;
import java.util.Scanner;
import dao.TransportDAO;
import model.*;


public class Runnable {
	
	private static Scanner sc = new Scanner(System.in);
	private static TransportDAO TD = new TransportDAO();
    
	public static void main(String[] args) {
		
		boolean isRunning = true;
		int pick = 8;
		while (isRunning) {
			System.out.println("Benvenuto nel gestionale, cosa vuoi fare? \n \n" 
					+ "1 - Gestione della sezione Utenti \n"
					+ "2 - Gestione dei Titoli di Viaggio \n"
					+ "3 - Gestione del Parco mezzi \n \n"
					+ "0 - Per uscire dal gestionale.");	  
			
			pick = chiediIntero(0, 3);
		
			switch (pick) {
				case 0 -> {
					System.out.println("Chiusura del gestionale");
					break;
				}
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
							System.out.println("Vuoi sottoscrivere una tessera utente? (utile all'acquisto di abbonamenti)");
							String risposta = chiediYorN();
							
							if (risposta.equals("y")) {
								TD.salvaEntita(u);
								Tessera t = new Tessera();
								TD.salvaEntita(t, u);
								System.out.println("Operazione eseguita correttamente");
							} else {
								TD.salvaEntita(u);
							}
						}
						
						case 2 -> {
							System.out.println("Quale utente vuoi modificare? \n");
							
							List<Utente> listUtenti = TD.trovaTuttiUtenti();
							for (Utente u : listUtenti) {
								System.out.println(u);
							}
							
							System.out.println("\nIndica l'utente attraverso il corrispettivo ID");
							Long id_utente = (long)chiediIntero(1, listUtenti.size());
							
							Utente u2 = TD.findUtente(id_utente);
							
							System.out.println("Quale proprietà vuoi modificare? \n"
									+ "1 - Nome \n"
									+ "2 - Cognome \n"
									+ "3 - Nome e Cognome \n");
							
							int daModificareUtente = chiediIntero(1, 3);
							
							if (daModificareUtente == 1) {
								System.out.println("Inserisci il nuovo nome \n");
								u2.setNome(sc.next());
								TD.aggiornaEntita(u2);
							} else if (daModificareUtente == 2) {
								System.out.println("Inserisci il nuovo cognome \n");
								u2.setCognome(sc.next());
								TD.aggiornaEntita(u2);
							} else {
								System.out.println("Inserisci il nuovo nome \n");
								u2.setNome(sc.next());
								System.out.println("Inserisci il nuovo cognome \n");
								u2.setCognome(sc.next());
								TD.aggiornaEntita(u2);
							}
 						}
						
						case 3 -> {
							System.out.println("Quale utente vuoi eliminare?");
							
							List<Utente> listUtenti = TD.trovaTuttiUtenti();
							for (Utente u : listUtenti) {
								System.out.println(u);
							}
							
							System.out.println("\nIndica l'utente attraverso il corrispettivo ID");
							Long id_utente = (long)chiediIntero(1, listUtenti.size());
							
							Utente u3 = TD.findUtente(id_utente);
							TD.eliminaEntita(u3);
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
	}

	public static int chiediIntero(int s, int f) {
		boolean isRunning = true;
		int pick = 5;
		while (isRunning) {
			System.out.println("\nInserisci tramite tastiera il valore corrispondente all'azione desiderata:");
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
	
	public static String chiediYorN() {
		boolean isRunning = true;
		String pick = "";
		while (isRunning) {
			System.out.print (" y / n \n");
			pick = sc.next();
			String[] pickArray = pick.split("");
			pick = pickArray[0].toLowerCase();
			if(pick.equals("y") || pick.equals("n")) {
				break;
			} else {
				System.out.println("caratteren non valido.");
				continue;
			}
		}
		return pick;
	}
	
}
