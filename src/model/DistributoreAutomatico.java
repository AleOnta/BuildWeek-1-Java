package model;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class DistributoreAutomatico extends VenditaBiglietto{
	@Enumerated(EnumType.STRING)
	private	E_distributoreAutomatico attivo;

	public E_distributoreAutomatico getAttivo() {
		return attivo;
	}

	public void setAttivo(E_distributoreAutomatico attivo) {
		this.attivo = attivo;
	}

	@Override
	public String toString() {
		return "DistributoreAutomatico [Id = " + getId_rivenditore() + ", attivo = " + attivo + ", Luogo = "
				+ getLuogo() + "]";
	}


}
