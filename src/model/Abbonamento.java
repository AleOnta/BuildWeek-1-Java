package model;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
public class Abbonamento extends Titolo_di_Viaggio {
	
	@ManyToOne
	@JoinColumn(name = "id_tessera")
	private Tessera tessera_proprietario;
	@Enumerated(EnumType.STRING)
	private E_Abbonamento tipologia;
	private LocalDate scadenza;

	public Abbonamento() {	
	}
	
	public Abbonamento(E_Abbonamento tipo) {
		this.tipologia = tipo;
		this.scadenza = defineExpiration(tipo);
	}
	
	private LocalDate defineExpiration(E_Abbonamento tipo) {
		if (tipo.toString().equals("SETTIMANALE")) {
			return LocalDate.now().plusWeeks(1);
		} else {
			return LocalDate.now().plusMonths(1);
		}
	}

	public Tessera getTessera_proprietario() {
		return tessera_proprietario;
	}

	public void setTessera_proprietario(Tessera tessera) {
		this.tessera_proprietario = tessera;
	}

	public E_Abbonamento getTipologia() {
		return tipologia;
	}

	public void setTipologia(E_Abbonamento tipologia) {
		this.tipologia = tipologia;
	}

	public LocalDate getScadenza() {
		return scadenza;
	}

	public void setScadenza(LocalDate scadenza) {
		this.scadenza = scadenza;
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
		return "Abbonamento [" + super.toString() + ", tesseraId=" + tessera_proprietario + ", tipologia=" + tipologia + ", scadenza=" + scadenza
				+"]";
	}
	
	
	
	
}
