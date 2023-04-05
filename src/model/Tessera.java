package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "tessere")
public class Tessera {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numero_tessera;
	private LocalDate iscrizione;
	private LocalDate scadenza;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_utente")
	private Utente utente_proprietario;
	@OneToMany(mappedBy = "tessera_proprietario", cascade = CascadeType.ALL)
	private List<Abbonamento> abbonamenti = new ArrayList<Abbonamento>();
	
	public Tessera() {
		super();
		this.iscrizione = LocalDate.now();
		this.scadenza=LocalDate.now().plusYears(1);
	}

	public Tessera(Utente utenteProprietario) {
		super();
		this.utente_proprietario = utenteProprietario;
		this.iscrizione = LocalDate.now();
		this.scadenza=LocalDate.now().plusYears(1);
	}

	public Utente getUtenteProprietario() {
		return utente_proprietario;
	}

	public void setUtenteProprietario(Utente utenteProprietario) {
		this.utente_proprietario = utenteProprietario;
	}

	public LocalDate getScadenza() {
		return scadenza;
	}

	public void setScadenza(LocalDate scadenza) {
		this.scadenza = scadenza;
	}

	public Long getNumeroTessera() {
		return numero_tessera;
	}
	
	public List<Abbonamento> getAbbonamenti() {
		System.out.print(abbonamenti + " - ");
		return abbonamenti;
	}

	@Override
	public String toString() {
		return "TESSERA --> numero-tessera=" + numero_tessera + ", utente-proprietario=" + utente_proprietario + ", scadenza="
				+ scadenza;
	}
	
}
