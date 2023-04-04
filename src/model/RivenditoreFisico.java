package model;

import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class RivenditoreFisico extends VenditaBiglietto{
private LocalDate orarioApertura;
private LocalDate orarioChiusura;
public LocalDate getOrarioApertura() {
	return orarioApertura;
}
public void setOrarioApertura(LocalDate orarioApertura) {
	this.orarioApertura = orarioApertura;
}
public LocalDate getOrarioChiusura() {
	return orarioChiusura;
}
public void setOrarioChiusura(LocalDate orarioChiusura) {
	this.orarioChiusura = orarioChiusura;
}
@Override
public String toString() {
	return "RivenditoreFisico [orarioApertura=" + orarioApertura + ", orarioChiusura=" + orarioChiusura
			+ ", getId_rivenditore()=" + getId_rivenditore() + "]";
}


}
