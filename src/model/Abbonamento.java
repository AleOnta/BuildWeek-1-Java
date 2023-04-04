package model;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "abbonamenti")
public class Abbonamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_abbonamento")
	private Long numeroAbbonamento;
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

	public Long getNumeroAbbonamento() {
		return numeroAbbonamento;
	}

	@Override
	public String toString() {
		return "Abbonamento [numeroAbbonamento=" + numeroAbbonamento + ", tipologia=" + tipologia + ", scadenza="
				+ scadenza + "]";
	}
	
	
	
}
