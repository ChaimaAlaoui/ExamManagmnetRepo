package com.ensah.bo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
public class Filiere {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long IdFiliere;
	private String nomFiliere ; 
	public Long getIdFiliere() {
		return IdFiliere;
	}

	public void setIdFiliere(Long idFiliere) {
		IdFiliere = idFiliere;
	}

	public String getNomFiliere() {
		return nomFiliere;
	}

	public void setNomFiliere(String nomFiliere) {
		this.nomFiliere = nomFiliere;
	}

	public List<Enseignant> getListeEnseignants() {
		return ListeEnseignants;
	}

	public void setListeEnseignants(List<Enseignant> listeEnseignants) {
		ListeEnseignants = listeEnseignants;
	}

	
	
	@OneToMany(mappedBy = "filiere", cascade = { CascadeType.PERSIST, CascadeType.MERGE }) 
 
	private List<Enseignant> ListeEnseignants =new ArrayList();
}
