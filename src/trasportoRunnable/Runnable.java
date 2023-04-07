package trasportoRunnable;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import dao.TransportDAO;
import model.*;
import model_parco_mezzi.*;


public class Runnable {
	
	private static Scanner sc = new Scanner(System.in);
	private static TransportDAO TD = new TransportDAO();
    
	public static void main(String[] args) {
		
		boolean isRunning = true;
		int pick = 8;
		while (isRunning) {
			System.out.println("\nBenvenuto nel gestionale, cosa vuoi fare? \n \n" 
					+ "1 - Gestione della sezione Utenti \n"
					+ "2 - Gestione dei Titoli di Viaggio \n"
					+ "3 - Gestione del Parco mezzi \n \n"
					+ "0 - Per uscire dal gestionale.");	  
			
			pick = chiediIntero(0, 3);
		
			switch (pick) {
				case 0 -> {
					System.out.println("Chiusura del gestionale");
					isRunning = false;
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
					System.out.println("Benvenuto nella gestione dei Titoli di viaggio, \n"
							+ "1 - Lavora con i biglietti \n"
							+ "2 - Lavora con gli abbonamenti \n"
							+ "3 - Lavora con le tessere \n"
							+ "4 - Stampa tutti i Titoli di viaggio \n"
							+ "5 - Stampa Titoli emessi in un determinato periodo \n");
					
					int pickGestioneTitoli = chiediIntero(1, 5);
					
					switch (pickGestioneTitoli) {
						case 1 -> {
							System.out.println("Benvenuto nella sezione 'biglietti', cosa vuoi fare? \n"
									+ "1 - Compra un nuovo biglietto \n"
									+ "2 - Elimina un biglietto \n"
									+ "3 - Stampa biglietti di un utente \n"
									+ "4 - Stampa biglietti emessi in un determinato periodo di tempo \n"
									+ "5 - Stampa tutti i biglietti \n");
							
							int pickOperazioneBiglietto = chiediIntero(1, 8);
							switch (pickOperazioneBiglietto) {
								case 1 -> {
									System.out.println("Tramite quale rivenditore vorresti acquistare il biglietto?");									
									List<VenditaBiglietto> listRivenditori = TD.trovaTuttiRivenditori();
									for (VenditaBiglietto r : listRivenditori) {
										System.out.println(r);
									}									
									System.out.println("\nIndica il rivenditore attraverso il corrispettivo ID");
									int daUtilizzare = chiediIntero(1, listRivenditori.size());
									VenditaBiglietto r = TD.findEmittente((long)daUtilizzare);									
									System.out.println("Per quale utente vuoi acquistare il biglietto? \n");									
									List<Utente> listUtenti = TD.trovaTuttiUtenti();
									for (Utente u : listUtenti) {
										System.out.println(u);
									}
									System.out.println("\nIndica l'utente attraverso il corrispettivo ID");
									Long id_utente = (long)chiediIntero(1, listUtenti.size());
									Utente u = TD.findUtente(id_utente);
									
									Biglietto b = new Biglietto();
									b.setEmittente(r);
									TD.salvaEntita(b, u);
								}								
								case 2 -> {
									System.out.println("Quale biglietto vorresti eliminare?");									
									List<Biglietto> listBiglietti = TD.trovaTuttiBiglietti();
									for (Biglietto b : listBiglietti) {
										System.out.println(b);
									}									
									System.out.println("\nIndica il biglietto attraverso il corrispettivo ID");
									Long id_biglietto = (long)chiediIntero(1, listBiglietti.size());									
									Biglietto b = TD.findBiglietto(id_biglietto);
									TD.eliminaEntita(b);
								}								
								case 3 -> {
									System.out.println("Di quale utente vorresti stampare i biglietti?");									
									List<Utente> listUtenti = TD.trovaTuttiUtenti();
									for (Utente u : listUtenti) {
										System.out.println(u);
									}									
									System.out.println("\nIndica l'utente attraverso il corrispettivo ID");
									Long id_utente = (long)chiediIntero(1, listUtenti.size());									
									Utente u = TD.findUtente(id_utente);									
									System.out.println("Ecco la lista di biglietti dell'utente: \n");
									List<Biglietto> bigliettiUtente = TD.trovaBigliettiUtente2(u);									
									for (Biglietto b : bigliettiUtente) {
										System.out.println(b);
									}
									System.out.println("\n\n");
								}
								case 4 -> { // FIX PATH
									System.out.println("Okay, inserisci le date seguendo il seguente formato: yyyy mm dd \n"
														+ "separandoli tramite uno spazio");									
									System.out.println("Cerca a partire da:");
									LocalDate dataUno = chiediData("inizio ricerca");
									LocalDate dataDue = chiediData("fine ricerca");
									
									List<Titolo_di_Viaggio> listBiglietti = TD.findBigliettiEmessiInData(dataUno, dataDue);
									
									for (Titolo_di_Viaggio t : listBiglietti) {
										if (t instanceof Biglietto) {
												System.out.println(t);
										}
									}
								}
								
								case 5 -> {
									List<Biglietto> tuttiBiglietti = TD.trovaTuttiBiglietti();
									for (Biglietto b : tuttiBiglietti) {
										System.out.println(b);
									}
								}
								
							}
						}
						
						case 2 -> {
							System.out.println("Benvenuto nella sezione 'abbonamenti', cosa vuoi fare? \n"
									+ "1 - Sottoscrivi abbonamento \n"
									+ "2 - Elimina un abbonamento \n"
									+ "3 - Trova abbonamenti per tipologia \n"
									+ "4 - Stampa tutti gli abbonamenti \n");
							
							int pickOperazioneAbbonamento = chiediIntero(1, 5);
							switch (pickOperazioneAbbonamento) {
								case 1 -> {
									System.out.println("Tramite quale rivenditore vorresti acquistare il biglietto?");
									
									List<VenditaBiglietto> listRivenditori = TD.trovaTuttiRivenditori();
									for (VenditaBiglietto r : listRivenditori) {
										if (r instanceof RivenditoreFisico) {
											System.out.println(r);
										}	
									}
									
									System.out.println("\nIndica il rivenditore attraverso il corrispettivo ID");
									int daUtilizzare = chiediIntero(1, listRivenditori.size());
									VenditaBiglietto r = TD.findEmittente((long)daUtilizzare);
									
									System.out.println("Per quale tessera vuoi sottoscrivere l'abbonamento? \n");
									
									List<Tessera> listTessere = TD.trovaTutteTessere();
									for (Tessera t : listTessere) {
										System.out.println(t);
									}
									System.out.println("\nIndica la tessera attraverso il corrispettivo ID");
									
									Long id_tessera = (long)chiediIntero(1, listTessere.size());
									Tessera t = TD.findTessera(id_tessera);
									
									System.out.println("Che tipo di abbonamento vuoi sottoscrivere? \n"
											+ "1 - Settimanale \n"
											+ "2 - Mensile \n");
									
									int pickTipo = chiediIntero(1, 2); 
									if (pickTipo == 1) {
										Abbonamento a = new Abbonamento(E_Abbonamento.SETTIMANALE);
										a.setEmittente(r);
										TD.salvaEntita(a, t);
									} else {
										Abbonamento a = new Abbonamento(E_Abbonamento.MENSILE);
										a.setEmittente(r);
										TD.salvaEntita(a, t);
									}
								}
								
								case 2 -> {
									System.out.println("Quale abbonamento vorresti eliminare?");
									
									List<Abbonamento> listAbbonamenti = TD.trovaTuttiAbbonamenti();
									for (Abbonamento a : listAbbonamenti) {
										System.out.println(a);
									}
									
									System.out.println("\nIndica il biglietto attraverso il corrispettivo ID");
									Long id_Abbonamento = (long)chiediIntero(1, listAbbonamenti.size());
									
									Abbonamento a = TD.findAbbonamento(id_Abbonamento);
									TD.eliminaEntita(a);
								}
								
								case 3 -> {
									System.out.println("Che tipo di abbobnamento vuoi ricercare? \n"
											+ "1 - Settimanale \n"
											+ "2 - Mensile \n");
									
									int pickTipo = chiediIntero(1, 2); 
									if (pickTipo == 1) {
										List<Abbonamento> listAbbonamento = TD.trovaAbbonamentiPerTipo(E_Abbonamento.SETTIMANALE);
										for (Abbonamento a : listAbbonamento) {
											System.out.println(a);
										}
									} else {
										List<Abbonamento> listAbbonamento = TD.trovaAbbonamentiPerTipo(E_Abbonamento.MENSILE);
										for (Abbonamento a : listAbbonamento) {
											System.out.println(a);
										}
									}
									
								}
								
								case 4 ->{
		                            System.out.println("Ecco tutti i titoli di viaggio");
		                            List <Titolo_di_Viaggio> listaTitoliDiViaggio = TD.trovaTuttiITitoliDiViaggio();
		                            for (Titolo_di_Viaggio t :listaTitoliDiViaggio)
		                                System.out.println(t);
		                        }
							}
						}
						case 2 ->{}
						case 3 ->{}
						case 4 ->{
							System.out.println("Ecco tutti i titoli di viaggio");
							List <Titolo_di_Viaggio> listaTitoliDiViaggio = TD.trovaTuttiITitoliDiViaggio();
							for (Titolo_di_Viaggio t :listaTitoliDiViaggio)
								System.out.println(t);
						}
					}				
				}
				// GESTIONE PARCO MEZZI
				case 3 -> {
					System.out.println("Benvenuto nella gestione del parco mezzi, \n"
							+ "1 - Gestisci i mezzi \n"
							+ "2 - Gestisci le tratte \n"
							+ "3 - Gestisci le manutenzioni \n");
					int pickGestioneParcoMezzi = chiediIntero(1, 3);
					
					switch (pickGestioneParcoMezzi) {
					case 1 -> {	
							//						 GESTIONE MEZZI
						System.out.println("Benvenuto nella gestione dei mezzi, \n"
						+ "1 - Crea un mezzo \n"
						+ "2 - Aggiona un mezzo \n"
						+ "3 - Elimina un mezzo \n"
						+ "4 - Mostra tutti i mezzi");
						int pickGestioneMezzi = chiediIntero(1, 4);
						switch(pickGestioneMezzi) {
						// 								CREAZIONE MEZZO
						case 1->{
							Veicolo v = new Veicolo();
							
							System.out.println("Inserisci tipologia mezzo, \n"
									+ "1 - Tram \n"
									+ "2 - Autobus \n ");
							int pickTipologiaMezzo = chiediIntero(1, 2);
							if(pickTipologiaMezzo == 1) {
								v.setTipologia(E_Veicolo.TRAM);
							}else v.setTipologia(E_Veicolo.AUTOBUS);
							System.out.println("Assegna un punto di partenza per la tratta \n");
							Tratta tr = new Tratta();							
							tr.setPunto_partenza(sc.next()+sc.nextLine());							
							System.out.println("Assegna un capolinea alla tratta \n");
							tr.setCapolinea(sc.nextLine());
							System.out.println("Assegna una percorrenza media della tratta \n");
							System.out.println("Inserisci ore (un integer) \n");
							int ora =sc.nextInt();
							System.out.println("Inserisci minuti (un integer) \n");
							int minuti = sc.nextInt();
							tr.setMedia_percorrenza(LocalTime.of(ora, minuti));
							TD.salvaEntita(v);
							TD.salvaEntita(tr, v);
						}case 2 ->{
							// 								AGGIORNAMENTO MEZZO
							System.out.println("Scegli il mezzo da aggiornare \n");
							
							 List <Veicolo> tuttiIVeicoli = TD.trovaTuttiIVeicoli();
							 for (Veicolo v  : tuttiIVeicoli) {
								 System.out.println(v);
							 }
							Long id = sc.nextLong();
							Veicolo v = TD.findVeicolo(id);
							System.out.println("Cosa desideri modificare \n"
									+ "1 - Tipologia \n"
									+ "2 - Status \n"
									+ "3 - Capienza \n"
									+ "4 - Tappa assegnata \n"
									+ "5 - Tutto");
							 int daModificareVeicolo= chiediIntero(1, 4);
							 if (daModificareVeicolo == 1) {
								 System.out.println("Inserisci tipologia mezzo, \n"
											+ "1 - Tram \n"
											+ "2 - Autobus \n ");
								int pickTipologiaMezzo = chiediIntero(1, 2);
								if(pickTipologiaMezzo == 1) {
									  v.setTipologia(E_Veicolo.TRAM);
								}else v.setTipologia(E_Veicolo.AUTOBUS);
								TD.aggiornaEntita(v);
								
							 }else if(daModificareVeicolo== 2) {
								 System.out.println("Inserisci status mezzo, \n"
											+ "1 - In Servizio \n"
											+ "2 - In Manutenzione \n ");
									int pickStatusMezzo = chiediIntero(1, 2);
									if(pickStatusMezzo == 1) {
										  v.setStatus(E_StatusVeicolo.IN_SERVIZIO);
									}else v.setStatus(E_StatusVeicolo.IN_MANUTENZIONE);
									TD.aggiornaEntita(v);
								 
							 }else if(daModificareVeicolo == 3) {
								 System.out.println("Seleziona la nuova capienza");
								 v.setCapienza(sc.nextInt());
								 TD.aggiornaEntita(v);
							 }else if (daModificareVeicolo == 4) {
								 System.out.println("Seleziona la tappa da assegnare ");
								 List<Tratta> listTratte = TD.trovaTutteLeTratte();
				                    for (Tratta tr : listTratte) {
				                        System.out.println(tr);		                       
				                    } Long id1 = sc.nextLong();
			    					Tratta tr = TD.findTratta(id1);
			    					v.setTappa_assegnata(tr);
			    					TD.aggiornaEntita(v);
							 }else if (daModificareVeicolo ==5){
								 System.out.println("Inserisci tipologia mezzo, \n"
											+ "1 - Tram \n"
											+ "2 - Autobus \n ");
								int pickTipologiaMezzo = chiediIntero(1, 2);
								if(pickTipologiaMezzo == 1) {
									  v.setTipologia(E_Veicolo.TRAM);
								}else v.setTipologia(E_Veicolo.AUTOBUS);
								 System.out.println("Inserisci status mezzo, \n"
											+ "1 - In Servizio \n"
											+ "2 - In Manutenzione \n ");
									int pickStatusMezzo = chiediIntero(1, 2);
									if(pickStatusMezzo == 1) {
										  v.setStatus(E_StatusVeicolo.IN_SERVIZIO);
									}else v.setStatus(E_StatusVeicolo.IN_MANUTENZIONE);
									System.out.println("Inserisci capienza veicolo \n");
									 v.setCapienza(sc.nextInt());
									 System.out.println("Seleziona la tappa da assegnare ");
									 List<Tratta> listTratte = TD.trovaTutteLeTratte();
					                    for (Tratta tr1 : listTratte) {
					                        System.out.println(tr1);}			                        			
				                        Long id1 = sc.nextLong();
				    					Tratta tr = TD.findTratta(id1);
				    					v.setTappa_assegnata(tr);	
				    					TD.aggiornaEntita(v);
							 }	//									FINE AGGIORNAMENTO MEZZO
							 
							 
							 
							 //                  				ELIMINAZIONE MEZZO
						} case 3 -> {
							System.out.println("Seleziona il mezzo da eliminare ");
							List <Veicolo> tuttiIVeicoli = TD.trovaTuttiIVeicoli();
							for (Veicolo v : tuttiIVeicoli) {
								System.out.println(v);
							}
							Long id = sc.nextLong();
							Veicolo v = TD.findVeicolo(id);
							
							TD.eliminaEntita(v);
							
							
						}case 4 ->{
							System.out.println("Ecco la lista di tutti  i veicoli \n");
							List <Veicolo> tuttiIVeicoli = TD.trovaTuttiIVeicoli();
							for (Veicolo v : tuttiIVeicoli) {
								System.out.println(v);
							}
						}													
						} //  													FINE GESTIONE MEZZI
						  
					}
					
					case 2 -> {
						// 														GESTIONE TRATTE
						System.out.println("Benvenuto nella gestione delle tratte, \n"
						+ "1 - Crea una tratta \n"
						+ "2 - Aggiona una tratta \n"
						+ "3 - Elimina una tratta \n"
						+ "4 - Mostra tutte le Tappe \n");
						int pickGestioneTratte = chiediIntero(1, 4);
						switch (pickGestioneTratte) {
						case 1 -> 
						//    										 			CREAZIONE TRATTA
						{System.out.println("Assegna un punto di partenza per la tratta \n");
						Tratta tr = new Tratta();
						
						tr.setPunto_partenza(sc.next()+sc.nextLine());
						
						System.out.println("Assegna un capolinea alla tratta \n");
						tr.setCapolinea(sc.nextLine());
						
						System.out.println("Assegna una percorrenza media della tratta \n");
						System.out.println("Inserisci ore (un integer) \n");
						int ora =sc.nextInt();
						System.out.println("Inserisci minuti (un integer) \n");
						int minuti = sc.nextInt();
						tr.setMedia_percorrenza(LocalTime.of(ora, minuti));
						System.out.println("Seleziona un mezzo da abbinare alla tratta creata \n");
						
						Veicolo v;
						System.out.println(TD.trovaTuttiIVeicoli()); 
						Long id = sc.nextLong();
						v = TD.findVeicolo(id);
						TD.salvaEntita(tr, v);
						}
						// 							MODIFICA TRATTA
						case 2 ->{
							Tratta tr ;
							System.out.println("Seleziona una tratta \n");
							List<Tratta> listTratte = TD.trovaTutteLeTratte();
		                    for (Tratta tr1 : listTratte) {
		                        System.out.println(tr1);
		                    }
							Long id = sc.nextLong();
							tr = TD.findTratta(id);
							System.out.println("Cosa desideri modificare \n"
									+ "1 - Capolinea \n"
									+ "2 - Punto di partenza \n"
									+ "3 - Percorrenza Media \n"
									+ "4 - Tutto \n");
							 int daModificareTratta= chiediIntero(1, 4);
							 if (daModificareTratta == 1) {
								 tr.setCapolinea(sc.nextLine());
								 
							 }else if(daModificareTratta== 2) {
								 tr.setPunto_partenza(sc.nextLine());
								 
							 }else if(daModificareTratta == 3) {
								 System.out.println("Inserisci ore (un integer) \n");
									int ora =sc.nextInt();
									System.out.println("Inserisci minuti (un integer) \n");
									int minuti = sc.nextInt();
									tr.setMedia_percorrenza(LocalTime.of(ora, minuti));
							 }else System.out.println("Assegna un punto di partenza per la tratta \n");
								tr.setPunto_partenza(sc.next()+sc.nextLine());
								
								System.out.println("Assegna un capolinea alla tratta \n");
								tr.setCapolinea(sc.nextLine());
								
								System.out.println("Assegna una percorrenza media della tratta \n");
								System.out.println("Inserisci ore (un integer) \n");
								int ora =sc.nextInt();
								System.out.println("Inserisci minuti (un integer) \n");
								int minuti = sc.nextInt();
								tr.setMedia_percorrenza(LocalTime.of(ora, minuti));
								TD.aggiornaEntita(tr);
						}
						//  								ELIMINAZIONE TRATTA
						case 3 -> {System.out.println("Seleziona la tratta da eliminare");
						List<Tratta> listTratte = TD.trovaTutteLeTratte();
		                for (Tratta tr1 : listTratte) {
		                    System.out.println(tr1);
		                }
		                Long id = sc.nextLong();
		                Tratta tr = TD.findTratta(id);
		                TD.eliminaEntita(tr);
		                //													TUTTE LE TRATTE
		                }case 4 ->{
						System.out.println("Ecco tutte le tratte");
						List<Tratta> listTratte = TD.trovaTutteLeTratte();
		                for (Tratta tr1 : listTratte) {
		                    System.out.println(tr1);
		                }
					}	
					}
						}
						//										MANUTENZIONI	
					case 3 -> {
						System.out.println("Benvenuto nella gestione delle manutenzioni, \n"
						+ "1 - Crea una manutenzione \n"
						+ "2 - Aggiona una manutenzione \n"
						+ "3 - Elimina una manutenzione \n");
						int pickGestioneManutenzioni = chiediIntero(1, 3);
						// 													CREAZIONE MANUTENZIONE
						if (pickGestioneManutenzioni == 1) {
							Manutenzione m = new Manutenzione();
							System.out.println("Seleziona il veicolo da mandare in manutenzione");
							List <Veicolo> tuttiIVeicoli = TD.trovaTuttiIVeicoli();
							for (Veicolo v : tuttiIVeicoli) {
			                    System.out.println(v);
			                }
							Long id = sc.nextLong();
							Veicolo v  = TD.findVeicolo(id);
							List <Manutenzione>manutenzioniVeicolo = v.getManutenzioni();
							System.out.println("Inserisci la data col seguente format YYYY MM DD");
						
							LocalDate inizio = chiediData("Inizio");
							m.setInizio(inizio);
							System.out.println("Scegli data di fine della manutenzione col seguente format YYYY MM DD");
							LocalDate fine = chiediData("Fine");
							m.setFine(fine);
							TD.salvaEntita(v, m);
							// 													MODIFICA MANUTENZIONE
						}else if (pickGestioneManutenzioni == 2) {
							Manutenzione m = new Manutenzione();
							System.out.println("Seleziona la manutenzione da modificare");
							List <Manutenzione>manutenzioniVeicolo = TD.trovaTutteLeManutenzioni();
							for (Manutenzione m1 : manutenzioniVeicolo) {
			                    System.out.println(m1);
			                }
							Long id = sc.nextLong();
							
							m = TD.findManutenzione(id);
							System.out.println("Inserisci la data col seguente format YYYY MM DD");
							LocalDate inizio = chiediData("Inizio");
							m.setInizio(inizio);
							System.out.println("Scegli data di fine della manutenzione col seguente format YYYY MM DD");
							LocalDate fine = chiediData("Fine");
							m.setFine(fine);
							TD.aggiornaEntita(m);
							// 												ELIMINA MANUTENZIONE
					}else if (pickGestioneManutenzioni == 3) {
						Manutenzione m;
						System.out.println("Seleziona la manutenzione da modificare");
						List <Manutenzione>manutenzioniVeicolo = TD.trovaTutteLeManutenzioni();
						for (Manutenzione m1 : manutenzioniVeicolo) {
		                    System.out.println(m1);
		                }
						Long id = sc.nextLong();
						
						m = TD.findManutenzione(id);
						TD.eliminaEntita(m);
					}
					} 
							
					}
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
	
	public static LocalDate chiediData(String tipoData) {
		System.out.println("inserisci la data di " + tipoData);
		String dataInString = sc.next() + sc.nextLine();
		String[] dataInArray = dataInString.split(" ");
		int year = Integer.parseInt(dataInArray[0]);
		int month = Integer.parseInt(dataInArray[1]);
		int day = Integer.parseInt(dataInArray[2]);
		return LocalDate.of(year, month, day);
	}
	
}
