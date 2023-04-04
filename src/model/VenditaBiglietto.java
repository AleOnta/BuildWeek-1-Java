package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table (name="rivenditore")
@Inheritance (strategy= InheritanceType.SINGLE_TABLE)
public class VenditaBiglietto implements Serializable{
@Id
@GeneratedValue (strategy=GenerationType.IDENTITY)
private Long id_rivenditore;

public Long getId_rivenditore() {
	return id_rivenditore;
}


}
