package model;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class Biglietto extends Titolo_di_Viaggio {

	@ManyToOne
	private Utente utente;
	@Column(nullable = true)
	private LocalDate utilizzo;
	private boolean valido;
	
	public Biglietto() {
		super();
	}
	
	public Biglietto(VenditaBiglietto emittente, LocalDate dataEmissione, Utente utente) {
		super(emittente, dataEmissione);
		this.utente = utente;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public LocalDate getUtilizzo() {
		return utilizzo;
	}

	public void setUtilizzo(LocalDate utilizzo) {
		this.utilizzo = utilizzo;
	}

	public boolean isValido() {
		return valido;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}

	@Override
	public VenditaBiglietto getEmittente() {
		// TODO Auto-generated method stub
		return super.getEmittente();
	}

	@Override
	public void setEmittente(VenditaBiglietto emittente) {
		// TODO Auto-generated method stub
		super.setEmittente(emittente);
	}

	@Override
	public LocalDate getDataEmissione() {
		// TODO Auto-generated method stub
		return super.getDataEmissione();
	}

	@Override
	public void setDataEmissione(LocalDate dataEmissione) {
		// TODO Auto-generated method stub
		super.setDataEmissione(dataEmissione);
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}

	@Override
	public String toString() {
		return "Biglietto [" + super.toString() + ", utente=" + utente + ", utilizzo=" + utilizzo + ", valido=" + valido + "]";
	}

	
	
	
}
