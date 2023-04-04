package model;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "tessere")
public class Tessera {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numeroTessera;
	private LocalDate iscrizione;
	private LocalDate scadenza;
	@OneToOne(mappedBy="tesseraUtente")
	private Utente utenteProprietario;
	
	
	public Tessera() {
		super();
	}

	public Tessera(Utente utenteProprietario) {
		super();
		this.utenteProprietario = utenteProprietario;
		this.iscrizione = LocalDate.now();
		this.scadenza=LocalDate.now().plusYears(1);
	}

	public Utente getUtenteProprietario() {
		return utenteProprietario;
	}

	public void setUtenteProprietario(Utente utenteProprietario) {
		this.utenteProprietario = utenteProprietario;
	}

	public LocalDate getScadenza() {
		return scadenza;
	}

	public void setScadenza(LocalDate scadenza) {
		this.scadenza = scadenza;
	}

	public Long getNumeroTessera() {
		return numeroTessera;
	}

	@Override
	public String toString() {
		return "TESSERA --> numero-tessera=" + numeroTessera + ", utente-proprietario=" + utenteProprietario + ", scadenza="
				+ scadenza;
	}
	
}
