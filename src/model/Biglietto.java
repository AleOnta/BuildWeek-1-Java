package model;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
public class Biglietto extends Titolo_di_Viaggio {

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "utente_id")
	private Utente utente_prop;
	@Column(nullable = true)
	private LocalDate utilizzo;
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

	public LocalDate getUtilizzo() {
		return utilizzo;
	}

	public void setUtilizzo(LocalDate utilizzo) {
		this.utilizzo = utilizzo;
		this.valido = false;
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
		LocalDate now = LocalDate.now();
		if (now.isAfter(dataEmissione)) {
			this.valido = true;
		} else {
			this.valido = false;
		}
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}

	@Override
	public String toString() {
		return "Biglietto [" + super.toString() + ", utente=" + utente_prop + ", utilizzo=" + utilizzo + ", valido=" + valido + "]";
	}

	
	
	
}
