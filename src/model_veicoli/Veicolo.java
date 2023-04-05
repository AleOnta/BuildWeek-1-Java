package model_veicoli;

import java.util.List;
import java.util.Map;

import javax.persistence.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDate;
import model.Biglietto;

@Entity
@Table(name = "veicoli")
@Inheritance (strategy= InheritanceType.SINGLE_TABLE)
public class Veicolo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_veicolo;
	private E_StatusVeicolo status;
	private List<Biglietto> bigliettiVidimati = new ArrayList<Biglietto>();
	private Map<LocalDate, LocalDate> manutenzioni = new HashMap<LocalDate, LocalDate>();
	
	public Veicolo() {
		super();
		status = E_StatusVeicolo.IN_SERVIZIO;
	}

	public Long getId_veicolo() {
		return id_veicolo;
	}

	public E_StatusVeicolo getStatus() {
		return status;
	}

	public void setStatus(E_StatusVeicolo status) {
		this.status = status;
	}

	public List<Biglietto> getBigliettiVidimati() {
		return bigliettiVidimati;
	}

	public void setBigliettiVidimati(List<Biglietto> bigliettiVidimati) {
		this.bigliettiVidimati = bigliettiVidimati;
	}

	public Map<LocalDate, LocalDate> getManutenzioni() {
		return manutenzioni;
	}

	public void setManutenzioni(Map<LocalDate, LocalDate> manutenzioni) {
		this.manutenzioni = manutenzioni;
	}

	@Override
	public String toString() {
		return "Veicolo [status=" + status + ", bigliettiVidimati=" + bigliettiVidimati + ", manutenzioni="
				+ manutenzioni + "]";
	}
	
	
}
