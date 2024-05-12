package com.ensah.bo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
public class Niveau {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long IdNiveau;
public String titre;
public Niveau (String _titre) {
	
	titre=_titre;
}
public Niveau () {
	
	
}
public Long getIdNiveau() {
	return IdNiveau;
}
public void setIdNiveau(Long idNiveau) {
	IdNiveau = idNiveau;
}
public String getTitre() {
	return titre;
}
public void setTitre(String titre) {
	this.titre = titre;
}



}
