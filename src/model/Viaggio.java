package model;

import java.time.LocalTime;

import javax.persistence.*;

import model_parco_mezzi.Veicolo;

@Entity
@NamedQuery(name= "Viaggi.TuttiIViaggi", query = "SELECT v FROM Viaggio v")
public class Viaggio {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	private Veicolo mezzo;
	
	private LocalTime tempo_percorrenza;
	
	
	public Viaggio() {
		super();
	}

	public Veicolo getMezzo() {
		return mezzo;
	}

	public void setMezzo(Veicolo mezzo) {
		this.mezzo = mezzo;
	}

	public LocalTime getTempo_percorrenza() {
		return tempo_percorrenza;
	}

	public void randomPercorrenza() {
		int random = (int) (Math.round(Math.random()*59)+1);
		this.tempo_percorrenza=LocalTime.of(1, random);
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Viaggio [id = " + id + ", idVeicolo = " + mezzo.getId_veicolo() + ", tempo_percorrenza = " + tempo_percorrenza + "]";
	}
	

	
}
