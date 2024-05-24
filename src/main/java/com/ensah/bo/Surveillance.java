package com.ensah.bo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
@Entity
public class Surveillance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long IdSurveillance;
	@ManyToOne
	private Enseignant enseignantCoordonneSurveillance;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "Enseignant__Surveillance", joinColumns = @JoinColumn(name = "id_Surveillance"), inverseJoinColumns = @JoinColumn(name = "id_Enseignant"))

	private List<Enseignant> enseignantSurveillanceList;
	@ManyToOne
	private Administrateur administrateur;
	@ManyToOne
	private Salle salle; 
	public Long getIdSurveillance() {
		return IdSurveillance;
	}
	public void setIdSurveillance(Long idSurveillance) {
		IdSurveillance = idSurveillance;
	}
	public Enseignant getEnseignantCoordonneSurveillance() {
		return enseignantCoordonneSurveillance;
	}
	public void setEnseignantCoordonneSurveillance(Enseignant enseignantCoordonneSurveillance) {
		this.enseignantCoordonneSurveillance = enseignantCoordonneSurveillance;
	}
	public List<Enseignant> getEnseignantSurveillanceList() {
		return enseignantSurveillanceList;
	}
	public void addEnseignant(Enseignant ens) {
		enseignantSurveillanceList.add(ens);
	}
	public void setEnseignantSurveillanceList(List<Enseignant> enseignantSurveillanceList) {
		this.enseignantSurveillanceList = enseignantSurveillanceList;
	}
	public Administrateur getCadreAdministrateur() {
		return administrateur;
	}
	public void setCadreAdministrateur(Administrateur cadreAdministrateur) {
		this.administrateur = cadreAdministrateur;
	}
	public Salle getSalle() {
		return salle;
	}
	public void setSalle(Salle salle) {
		this.salle = salle;
	}
	public Examen getExamen() {
		return examen;
	}
	public void setExamen(Examen examen) {
		this.examen = examen;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_examen")
	private Examen examen ;
} 
