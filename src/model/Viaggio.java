package model;

import java.time.LocalTime;

import javax.persistence.*;

import model_parco_mezzi.Veicolo;

@Entity

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


	@Override
	public String toString() {
		return "Viaggio [id=" + id + ", mezzo=" + mezzo + ", tempo_percorrenza=" + tempo_percorrenza + "]";
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
	

}
