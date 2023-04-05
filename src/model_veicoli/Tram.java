package model_veicoli;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import model.Biglietto;

@Entity
public class Tram extends Veicolo {

	private int capienza = 40;
	
	public Tram() {
		super();
	}

	@Override
	public Long getId_veicolo() {
		// TODO Auto-generated method stub
		return super.getId_veicolo();
	}

	@Override
	public E_StatusVeicolo getStatus() {
		// TODO Auto-generated method stub
		return super.getStatus();
	}

	@Override
	public void setStatus(E_StatusVeicolo status) {
		// TODO Auto-generated method stub
		super.setStatus(status);
	}

	@Override
	public List<Biglietto> getBigliettiVidimati() {
		// TODO Auto-generated method stub
		return super.getBigliettiVidimati();
	}

	@Override
	public void setBigliettiVidimati(List<Biglietto> bigliettiVidimati) {
		// TODO Auto-generated method stub
		super.setBigliettiVidimati(bigliettiVidimati);
	}

	@Override
	public Map<LocalDate, LocalDate> getManutenzioni() {
		// TODO Auto-generated method stub
		return super.getManutenzioni();
	}

	@Override
	public void setManutenzioni(Map<LocalDate, LocalDate> manutenzioni) {
		// TODO Auto-generated method stub
		super.setManutenzioni(manutenzioni);
	}

	public int getCapienza() {
		return capienza;
	}

	public void setCapienza(int capienza) {
		this.capienza = capienza;
	}

	@Override
	public String toString() {
		return "Tram [Id=" + getId_veicolo() + ", Status=" + getStatus()
				+ ", BigliettiVidimati=" + getBigliettiVidimati() + ", Manutenzioni=" + getManutenzioni()
				+ ", Capienza=" + getCapienza() + ", capienza=" + capienza +"]";
	}
}

