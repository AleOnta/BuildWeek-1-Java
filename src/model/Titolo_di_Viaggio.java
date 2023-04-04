package model;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "titoli_di_viaggio")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_titolo", discriminatorType = DiscriminatorType.STRING)
public class Titolo_di_Viaggio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private VenditaBiglietto emittente;
	@Column(name = "data_emissione")
	private LocalDate dataEmissione;
		
	public Titolo_di_Viaggio() {
		super();
	}

	public Titolo_di_Viaggio(VenditaBiglietto emittente, LocalDate dataEmissione) {
		super();
		this.emittente = emittente;
		this.dataEmissione = dataEmissione;
	}

	public VenditaBiglietto getEmittente() {
		return emittente;
	}

	public void setEmittente(VenditaBiglietto emittente) {
		this.emittente = emittente;
	}

	public LocalDate getDataEmissione() {
		return dataEmissione;
	}

	public void setDataEmissione(LocalDate dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "id=" + id + ", emittente=" + emittente + ", dataEmissione=" + dataEmissione;
	}
	
	
	
}
