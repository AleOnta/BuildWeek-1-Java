package model_parco_mezzi;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "manutenzioni")
public class Manutenzione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_veicolo")
	private Veicolo id_veicolo;
	
	@Column(nullable = false)
	private LocalDate inizio;
	
	@Column(nullable = false)
	private LocalDate fine;
	
	public Manutenzione() {
		super();
	}

	public Veicolo getId_veicolo() {
		return id_veicolo;
	}

	public void setId_veicolo(Veicolo id_veicolo) {
		this.id_veicolo = id_veicolo;
	}

	public LocalDate getInizio() {
		return inizio;
	}

	public void setInizio(LocalDate inizio) {
		this.inizio = inizio;
	}

	public LocalDate getFine() {
		return fine;
	}

	public void setFine(LocalDate fine) {
		this.fine = fine;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Manutenzione [id=" + id + ", id_veicolo=" + id_veicolo + ", inizio=" + inizio + ", fine=" + fine + "]";
	}
	
}
