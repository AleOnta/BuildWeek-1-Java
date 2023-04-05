package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


@Entity
@Table (name="rivenditori")
@Inheritance (strategy= InheritanceType.SINGLE_TABLE)
public class VenditaBiglietto implements Serializable {
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long id_rivenditore;
	private String luogo;
	@OneToMany(mappedBy = "emittente", cascade = CascadeType.ALL)
	private List<Biglietto> bigliettiVenduti;
	
	
	public VenditaBiglietto() {
		super();
	}
	
	public String getLuogo() {
		return luogo;
	}
	
	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}
	
	public Long getId_rivenditore() {
		return id_rivenditore;
	}
	
	public void vendiBiglietto() {
	}
	
	
}
