package model;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Table(name = "utenti")
@NamedQuery(name = "utenti.findBigliettiUtente", query = "SELECT b FROM Biglietto b")
@NamedQuery(name = "utenti.findTessereUtente", query = "SELECT t FROM Tessera t WHERE t.utente_proprietario.id_user = :id")
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long id_user;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String cognome;
	
	@OneToMany(mappedBy = "utente_proprietario", cascade = CascadeType.ALL)
	private List<Tessera> tessera_utente = new ArrayList<Tessera>();
	
	@OneToMany(mappedBy="utente_prop", cascade = CascadeType.ALL)
	private List<Biglietto> biglietti = new ArrayList<Biglietto>();
	
	
	public Utente() {
	}
	
	public Utente(String nome, String cognome) {
		super();
		this.nome = nome;
		this.cognome = cognome;
	};

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

	public List<Tessera> getTessereUtente() {
		return tessera_utente;
	}

	public void setTesseraUtente(Tessera tesseraUtente) {
        this.tessera_utente.add(tesseraUtente);
    }

	public List<Biglietto> getBiglietti() {
		return biglietti;
	}

	public void setBiglietti(List<Biglietto> biglietti) {
		this.biglietti = biglietti;
	}

	public void printBiglietti() {
			System.out.println(biglietti);
	}
	
	@Override
	public String toString() {
		return "UTENTE --> id=" + id_user + ", n=" + nome + ", c=" + cognome + ", " + "]";
	}
	
	
}
