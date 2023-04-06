package model;

import java.time.LocalDate;

import javax.persistence.*;

import model_parco_mezzi.Veicolo;

@Entity
@Table(name = "convalidazioni")

public class Convalida {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_convalida;
	private LocalDate data_convalida =  LocalDate.now();;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_biglietto")
	private Biglietto biglietto;
	@ManyToOne
	@JoinColumn(name = "id_veicolo")
	private Veicolo convalidato_su;
	
	public Convalida() {
		super();
	}

	public Convalida(Biglietto biglietto, Veicolo convalidato_su) {
		super();
		this.biglietto = biglietto;
		this.convalidato_su = convalidato_su;
	}

	public Biglietto getBiglietto() {
		return biglietto;
	}

	public void setBiglietto(Biglietto biglietto) {
		this.biglietto = biglietto;
	}

	public Veicolo getConvalidato_su() {
		return convalidato_su;
	}

	public void setConvalidato_su(Veicolo convalidato_su) {
		this.convalidato_su = convalidato_su;
	}

	public Long getId_convalida() {
		return id_convalida;
	}
	
	public LocalDate getData_convalida() {
		return data_convalida;
	}

	public void setData_convalida(LocalDate data_convalida) {
		this.data_convalida = data_convalida;
	}

	public void convalidaBiglietto() {
		
	}

	@Override
	public String toString() {
		return "Convalida [id_convalida=" + id_convalida + ", biglietto=" + biglietto + ", convalidato_su="
				+ convalidato_su + "]";
	}
	
	
	
}
