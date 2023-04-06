package model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@Entity

public class Biglietto extends Titolo_di_Viaggio {

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "utente_id")
	private Utente utente_prop;
	@OneToMany(mappedBy = "biglietto", cascade = CascadeType.ALL)
	private List<Convalida> utilizzo;
	private boolean valido;
	
	public Biglietto() {
		super();
	}
	
	public Biglietto(VenditaBiglietto emittente, LocalDate dataEmissione, Utente utente) {
		super(emittente, dataEmissione);
		this.utente_prop = utente;
	}

	public Utente getUtente() {
		return utente_prop;
	}

	public void setUtente(Utente utente) {
		this.utente_prop = utente;
	}

	
	public String getUtilizzo() {
		if (utilizzo.size() > 0) {
			return utilizzo.get(0).getId_convalida().toString();
		} else {
			return "non convalidato";
		}
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
	public Long getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}

	@Override
	public String toString() {
		return "Biglietto [Id = " + getId() + ", idUtenteProprietario = " + utente_prop.getId() + ", Emittente = " + getEmittente() 
		+ ", DataEmissione = " + getDataEmissione() + ", validità = " + valido + ", convalida = " + getUtilizzo() + "]";
	}

	

	
	
	
}
