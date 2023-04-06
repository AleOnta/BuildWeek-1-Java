package model_parco_mezzi;

import java.time.LocalTime;

import javax.persistence.*;
@Entity
@Table (name = "tratte")
public class Tratta {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String punto_partenza;
	private String capolinea;
	private LocalTime media_percorrenza;
	
	@OneToOne(mappedBy="tappa_assegnata", cascade= CascadeType.ALL)
	private Veicolo veicolo;
	
	public Tratta() {
		super();
	}
	

	public String getPunto_partenza() {
		return punto_partenza;
	}

	public void setPunto_partenza(String punto_partenza) {
		this.punto_partenza = punto_partenza;
	}

	public String getCapolinea() {
		return capolinea;
	}

	public void setCapolinea(String capolinea) {
		this.capolinea = capolinea;
	}

	public LocalTime getMedia_percorrenza() {
		return media_percorrenza;
	}

	public void setMedia_percorrenza(LocalTime media_percorrenza) {
		this.media_percorrenza = media_percorrenza;
	}

	public Veicolo getVeicolo() {
		return veicolo;
	}

	public void setVeicolo(Veicolo veicolo) {
		this.veicolo = veicolo;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Tratta [id=" + id + ", punto_partenza=" + punto_partenza + ", capolinea=" + capolinea
				+ ", media_percorrenza=" + media_percorrenza + ", veicolo=" + veicolo + "]";
	}
	
	
	
}
