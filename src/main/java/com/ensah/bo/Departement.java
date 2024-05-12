package com.ensah.bo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.*;
@Entity
public class Departement {
	public Long getIdDepartement() {
		return IdDepartement;
	}
	public void setIdDepartement(Long idDepartement) {
		IdDepartement = idDepartement;
	}
	public String getNomDepartement() {
		return nomDepartement;
	}
	public void setNomDepartement(String nomDepartement) {
		this.nomDepartement = nomDepartement;
	}
	public List<Enseignant> getListeEnseignants() {
		return ListeEnseignants;
	}
	public void setListeEnseignants(List<Enseignant> listeEnseignants) {
		ListeEnseignants = listeEnseignants;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long IdDepartement;
	private String nomDepartement ;
	@OneToMany(mappedBy = "departement", cascade = { CascadeType.PERSIST, CascadeType.MERGE }) 
	private List<Enseignant> ListeEnseignants =new ArrayList();
}
