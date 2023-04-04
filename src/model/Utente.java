package model;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "utenti")
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long id_user;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String cognome;
	@OneToMany(mappedBy = "utente_proprietario")
	private Set<Tessera> tessera_utente;
	@OneToMany
	private List<Biglietto> biglietti;
	
	
	public Utente() {
	}
	
	public Utente(String nome, String cognome) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		//this.tesseraUtente.setUtenteProprietario(this);
	};

	
	public Utente(String nome, String cognome, Tessera tesseraUtente) { 
		super();
		this.nome = nome; 
		this.cognome = cognome; 
		
	}
	 

	public Long getId() {
		return id_user;
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

	public Set<Tessera> getTessereUtente() {
		return tessera_utente;
	}

	public void setTesseraUtente(Set<Tessera> tesseraUtente) {
		this.tessera_utente = tesseraUtente;
	}

	public List<Biglietto> getBiglietti() {
		return biglietti;
	}

	public void setBiglietti(List<Biglietto> biglietti) {
		this.biglietti = biglietti;
	}

	@Override
	public String toString() {
		return "UTENTE --> id=" + id_user + ", nome=" + nome + ", cognome=" + cognome + ", tessera-utente=" + tessera_utente
				+ ", biglietti=" + biglietti + "]";
	}
	
	
}
