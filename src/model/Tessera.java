package model;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "tessere")
public class Tessera {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numero_tessera;
	private LocalDate iscrizione;
	private LocalDate scadenza;
	@ManyToOne
	@JoinColumn(name = "id_utente")
	private Utente utente_proprietario;
	
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

	@Override
	public String toString() {
		return "TESSERA --> numero-tessera=" + numero_tessera + ", utente-proprietario=" + utente_proprietario + ", scadenza="
				+ scadenza;
	}
	
}
