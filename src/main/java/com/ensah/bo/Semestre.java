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
public class Semestre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long IdSemestre;
	public Long getIdSemestre() {
		return IdSemestre;
	}
	public void setIdSemestre(Long idSemestre) {
		IdSemestre = idSemestre;
	}
	public String getNomSemestre() {
		return nomSemestre;
	}
	public void setNomSemestre(String nomSemestre) {
		this.nomSemestre = nomSemestre;
	}
	public List<Examen> getListeExamens() {
		return ListeExamens;
	}
	public void setListeExamens(List<Examen> listeExamens) {
		ListeExamens = listeExamens;
	}
	private String nomSemestre ; 
	@OneToMany(mappedBy = "semestre", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Examen> ListeExamens =new ArrayList();
}
