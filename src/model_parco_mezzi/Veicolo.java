package model_parco_mezzi;

import model.Convalida;
import model.Viaggio;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import dao.TransportDAO;

import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDate;
import model.Biglietto;

@Entity
@Table(name = "veicoli")
@NamedQuery(name="Veicoli.CercaTuttiIVeicoli", query= "SELECT v FROM Veicolo v")
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
	
	@OneToOne(cascade=CascadeType.ALL)
	private Tratta tappa_assegnata;
	
	@OneToMany(mappedBy="mezzo",cascade=CascadeType.ALL)
	private List<Viaggio> lista_viaggi = new ArrayList<Viaggio>();
	
	@Column(nullable = false)
	private int counter;
	
	
	public void startViaggio() {
		counter++;
		Viaggio v = new Viaggio();
		v.setMezzo(this);
		v.randomPercorrenza();
		TransportDAO TD = new TransportDAO();
		TD.salvaEntita(v, this);
	}
	
	public Veicolo() {
		super();
		status = E_StatusVeicolo.IN_SERVIZIO;
		tipologia = E_Veicolo.AUTOBUS;
		capienza = 65;
		counter =0;
	}
	
	public Veicolo(E_Veicolo type) {
		super();
		status = E_StatusVeicolo.IN_SERVIZIO;
		tipologia = type;
		capienza = setCapienzaByTipo(type);
		counter =0;
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
	
	
	public void setTappa_assegnata(Tratta tappa_assegnata) {
		this.tappa_assegnata = tappa_assegnata;
	}

	public List<Convalida> getBigliettiVidimati() {
		return bigliettiVidimati;
	}

	public void printBigliettiVidimati() {
		if (bigliettiVidimati.size() > 0) {
			for (Convalida c : bigliettiVidimati) {
				System.out.println(c);
			}
		}
	}
	
	public void printManutenzioni() {
		if(manutenzioni.size() > 0) {
			for (Manutenzione m : manutenzioni) {
				System.out.println(m);
			}
		}
	}
	
	public List<Manutenzione> getManutenzioni() {
		return manutenzioni;
	}

	public void setManutenzioni(List<Manutenzione> manutenzioni) {
		this.manutenzioni = manutenzioni;
	}

	public List<Viaggio> getLista_viaggi() {
		return lista_viaggi;
	}

	public void setLista_viaggi(List<Viaggio> lista_viaggi) {
		this.lista_viaggi = lista_viaggi;
	}

	public Tratta getTappa_assegnata() {
		return tappa_assegnata;
	}

	public Long getId_veicolo() {
		return id_veicolo;
	}

	@Override
	public String toString() {
		return "Veicolo [id = " + id_veicolo + ", tipologia = " + tipologia + ", status = " + status + ", capienza = "
				+ capienza + ", convalide = " + bigliettiVidimati.size() + ", manutenzioni = " + manutenzioni.size()
				+ ", tappa_assegnata = " + tappa_assegnata + ", lista_viaggi = " + lista_viaggi.size() + ", counter = " + counter
				+ "]";
	}
}
