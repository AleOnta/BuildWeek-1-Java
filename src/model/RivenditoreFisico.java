package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class RivenditoreFisico extends VenditaBiglietto {
	
@Column(name = "ora_apertura", nullable = true)
private Time orarioApertura;
@Column(name = "ora_chiusura", nullable = true)
private Time orarioChiusura;
	
	public Time getOrarioApertura() {
		return orarioApertura;
	}
	
	public void setOrarioApertura(LocalTime orarioApertura) {
		Time time = Time.valueOf(orarioApertura);
		this.orarioApertura = time;
	}
	
	public Time getOrarioChiusura() {
		return orarioChiusura;
	}
	
	public void setOrarioChiusura(LocalTime orarioChiusura) {
		Time time = Time.valueOf(orarioChiusura);
		this.orarioChiusura = time;
	}
	
	@Override
	public String toString() {
		return "RivenditoreFisico [orarioApertura=" + orarioApertura + ", orarioChiusura=" + orarioChiusura
				+ ", id_rivenditore=" + super.getId_rivenditore() + "]";
	}

	public Abbonamento vendiAbbonamento(Utente cliente, E_Abbonamento tipo) {
		Abbonamento newAbb = null;
		if (cliente.getTessereUtente().size() < 1) {
			System.out.println("Utente sprovvisto di tessera, prima di acquistare un biglietto dovrà ottenerne una.");
		} else {
			newAbb = new Abbonamento();
			List<Tessera> tessereUtente = cliente.getTessereUtente();
			boolean isValid = tessereUtente.get(0).getScadenza().isAfter(LocalDate.now());
			if (isValid) {
				newAbb.setTessera_proprietario(tessereUtente.get(0));
				newAbb.setTipologia(tipo);
				newAbb.setEmittente(this);
			} else {
				System.out.println("la tessera è ormai scaduta, la stiamo rinnovando per un altro anno");
				tessereUtente.get(0).setScadenza(LocalDate.now().plusYears(1));
				newAbb.setTessera_proprietario(tessereUtente.get(0));
				newAbb.setTipologia(tipo);
				newAbb.setEmittente(this);
			}
			
		}
		return newAbb;
	}

	@Override
	public void vendiBiglietto() {
		//metodo per la vendita del biglietto
	}

	
}
