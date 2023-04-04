package model;

import java.util.List;

import javax.persistence.*;

//@Entity
//@Table(name = "utenti")
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String cognome;
	@OneToMany
	@Column(name = "id_tessera", nullable = true)
	private Tessera tesseraUtente;
	@OneToMany
	private List<Biglietto> biglietti;
	
	public Utente() {
		
	}

	public Utente(String nome, String cognome, Tessera tesseraUtente) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.tesseraUtente = tesseraUtente;
	}

	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Tessera getTesseraUtente() {
		return tesseraUtente;
	}

	public void setTesseraUtente(Tessera tesseraUtente) {
		this.tesseraUtente = tesseraUtente;
	}

	public List<Biglietto> getBiglietti() {
		return biglietti;
	}

	public void setBiglietti(List<Biglietto> biglietti) {
		this.biglietti = biglietti;
	}

	@Override
	public String toString() {
		return "UTENTE --> id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", tessera-utente=" + tesseraUtente
				+ ", biglietti=" + biglietti + "]";
	}
	
	
}
