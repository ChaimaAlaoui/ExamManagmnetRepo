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
public class TypeExamen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long IdTypeExamen;
	public String titre ;
	public Long getIdTypeExamen() {
		return IdTypeExamen;
	}

	public void setIdTypeExamen(Long idTypeExamen) {
		IdTypeExamen = idTypeExamen;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	@OneToMany(mappedBy = "typeExamen", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Examen> ListeExamens =new ArrayList();

	public List<Examen> getListeExamens() {
		return ListeExamens;
	}

	public void setListeExamens(List<Examen> listeExamens) {
		ListeExamens = listeExamens;
	}
	
}