package model_parco_mezzi;

import model.Convalida;
import java.util.List;
import java.util.Map;

import javax.persistence.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDate;
import model.Biglietto;

@Entity
@Table(name = "veicoli")
public class Veicolo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_veicolo;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private E_Veicolo tipologia;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private E_StatusVeicolo status;
	
	@Column(nullable = false)
	private int capienza;
	
	@OneToMany(mappedBy = "convalidato_su", cascade = CascadeType.ALL) 
	private List<Convalida> bigliettiVidimati = new ArrayList<Convalida>();
	
	@OneToMany(mappedBy = "id_veicolo", cascade = CascadeType.ALL)
	private List<Manutenzione> manutenzioni = new ArrayList<Manutenzione>();
	
	public Veicolo() {
		super();
		status = E_StatusVeicolo.IN_SERVIZIO;
		tipologia = E_Veicolo.AUTOBUS;
		capienza = 65;
	}
	
	public Veicolo(E_Veicolo type) {
		super();
		status = E_StatusVeicolo.IN_SERVIZIO;
		tipologia = type;
		capienza = setCapienzaByTipo(type);
	}

	public int setCapienzaByTipo(E_Veicolo type) {
		if (type.getTipo() == "TRAM") {
			return 40;
		} else {
			return 65;
		}
	}
	
	public void checkStatus(Manutenzione m) {
		LocalDate ora = LocalDate.now();
		boolean isAfter = ora.isAfter(m.getInizio());
		boolean isBefore = ora.isBefore(m.getFine());
		if (isAfter == true && isBefore == true) {
			this.setStatus(E_StatusVeicolo.IN_MANUTENZIONE);
			
		} else {
			this.setStatus(E_StatusVeicolo.IN_SERVIZIO);
		}
	}
	
	public E_Veicolo getTipologia() {
		return tipologia;
	}

	public void setTipologia(E_Veicolo tipologia) {
		this.tipologia = tipologia;
	}

	public int getCapienza() {
		return capienza;
	}

	public void setCapienza(int capienza) {
		this.capienza = capienza;
	}

	public E_StatusVeicolo getStatus() {
		return status;
	}

	public void setStatus(E_StatusVeicolo status) {
		this.status = status;
	}
	/*
	public List<Biglietto> getBigliettiVidimati() {
		return bigliettiVidimati;
	}

	public void setBigliettiVidimati(Biglietto bigliettoVidimato) {
		this.bigliettiVidimati.add(bigliettoVidimato);
	}
	*/
	public Long getId_veicolo() {
		return id_veicolo;
	}

	@Override
	public String toString() {
		return "Veicolo [id_veicolo=" + id_veicolo + ", tipologia=" + tipologia + ", capienza=" + capienza + ", status="
				+ status  + "]";
	}

}
