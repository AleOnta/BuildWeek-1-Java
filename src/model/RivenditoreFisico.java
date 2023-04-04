package model;

import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class RivenditoreFisico extends VenditaBiglietto{
private double orarioApertura;
private double orarioChiusura;
public double getOrarioApertura() {
	return orarioApertura;
}
public void setOrarioApertura(double orarioApertura) {
	this.orarioApertura = orarioApertura;
}
public double getOrarioChiusura() {
	return orarioChiusura;
}
public void setOrarioChiusura(double orarioChiusura) {
	this.orarioChiusura = orarioChiusura;
}
@Override
public String toString() {
	return "RivenditoreFisico [orarioApertura=" + orarioApertura + ", orarioChiusura=" + orarioChiusura
			+ ", getId_rivenditore()=" + getId_rivenditore() + "]";
}


}
