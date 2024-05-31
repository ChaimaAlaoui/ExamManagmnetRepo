package com.ensah.bo;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Examen {
	 @Lob
	    private byte[] PV;
	 @Lob
	    private byte[] Epreuve;
	 @ManyToOne
		private Enseignant enseignantCoordonne;
	 private String rapport ;
	public String getRapport() {
		return rapport;
	}
	public Enseignant getEnseignantCoordonne() {
		return enseignantCoordonne;
	}
	public void setEnseignantCoordonne(Enseignant enseignantCoordonne) {
		this.enseignantCoordonne = enseignantCoordonne;
	}
	public void setRapport(String rapport) {
		this.rapport = rapport;
	}
	public byte[] getPV() {
		return PV;
	}
	public void setPV(byte[] pV) {
		PV = pV;
	}
	public byte[] getEpreuve() {
		return Epreuve;
	}
	public void setEpreuve(byte[] epreuve) {
		Epreuve = epreuve;
	}
	private String  titreExamen;
private String anneeUniversitaire;
	
	public String getAnneeUniversitaire() {
		return anneeUniversitaire;
	}
	public void setAnneeUniversitaire(String anneeUniversitaire) {
		this.anneeUniversitaire = anneeUniversitaire;
	}
	public String getTitreExamen() {
		return titreExamen;
	}
	public void setTitreExamen(String titreExamen) {
		this.titreExamen = titreExamen;
	}
	public Long getIdExamen() {
		return IdExamen;
	}
	public void setIdExamen(Long idExamen) {
		IdExamen = idExamen;
	}
	public List<Surveillance> getListeSurveillance() {
		return ListeSurveillance;
	}
	public void setListeSurveillance(List<Surveillance> listeSurveillance) {
		ListeSurveillance = listeSurveillance;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getDurationMinutes() {
		return durationMinutes;
	}
	public void setDurationMinutes(int durationMinutes) {
		this.durationMinutes = durationMinutes;
	}
	public int getRealDurationMinutes() {
		return realDurationMinutes;
	}
	public void setRealDurationMinutes(int realDurationMinutes) {
		this.realDurationMinutes = realDurationMinutes;
	}
	public TypeExamen getTypeExamen() {
		return typeExamen;
	}
	public void setTypeExamen(TypeExamen typeExamen) {
		this.typeExamen = typeExamen;
	}
	public Semestre getSemestre() {
		return semestre;
	}
	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long IdExamen;
@OneToMany(mappedBy = "examen", cascade = { CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE })
private List<Surveillance> ListeSurveillance=new ArrayList<>();

@ManyToOne
private TypeExamen typeExamen;
@ManyToOne
private Semestre semestre;
@ManyToOne
private Session session;
@ManyToOne  
private ElementPedagogique elementP ;
private Date date;
private String heur;
public String getHeur() {
	return heur;
}
public void setHeur(String heur) {
	this.heur = heur;
}
private int durationMinutes;
private int realDurationMinutes;
public ElementPedagogique getElementP() {
	return elementP;
}
public void setElementP(ElementPedagogique elementP) {
	this.elementP = elementP;
}
}