package com.ensah.bo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.*;
@Entity
public class Groupe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdGroupe;
	private String nomGroupe ;
	//we find many prof in one grp
	@OneToMany(mappedBy = "groupe", cascade = { CascadeType.PERSIST, CascadeType.MERGE }) 
	private List<Enseignant> ListeEnseignants =new ArrayList();
	
	
	public Long getIdGroupe() {
		return IdGroupe;
	}
	public void setIdGroupe(Long idGroupe) {
		IdGroupe = idGroupe;
	}
	public String getNomGroupe() {
		return nomGroupe;
	}
	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}
	public List<Enseignant> getListeEnseignants() {
		return ListeEnseignants;
	}
	public void setListeEnseignants(List<Enseignant> listeEnseignants) {
		ListeEnseignants = listeEnseignants;
	}
	
}
