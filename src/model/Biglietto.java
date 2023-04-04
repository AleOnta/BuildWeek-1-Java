package model;

import java.time.LocalDate;

import javax.persistence.*;

public class Biglietto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "biglietto_id")
	private Long bigliettoId;
	@ManyToOne
	private Utente utente;
	private LocalDate dataEmissione;
	// ho messo string perchè non sapevo come volessi gestire le classi di vendita
	private String enteEmittente;
	@Column(nullable = true)
	private LocalDate utilizzo;
	private boolean valido;
	
	public Biglietto() {
		super();
	}
	
	public Biglietto(Utente utente, LocalDate dataEmissione, String enteEmittente) {
		super();
		this.utente = utente;
		this.dataEmissione = dataEmissione;
		this.enteEmittente = enteEmittente;
	}
	
	public Utente getUtente() {
		return utente;
	}
	
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	public LocalDate getDataEmissione() {
		return dataEmissione;
	}
	
	public void setDataEmissione(LocalDate dataEmissione) {
		this.dataEmissione = dataEmissione;
	}
	
	public String getEnteEmittente() {
		return enteEmittente;
	}
	
	public void setEnteEmittente(String enteEmittente) {
		this.enteEmittente = enteEmittente;
	}
	
	public LocalDate getUtilizzo() {
		return utilizzo;
	}
	
	public void setUtilizzo(LocalDate utilizzo) {
		this.utilizzo = utilizzo;
	}
	
	public Long getBigliettoId() {
		return bigliettoId;
	}
}
