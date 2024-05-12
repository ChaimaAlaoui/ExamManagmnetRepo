package com.ensah.bo;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Examen {
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
@OneToMany(mappedBy = "examen", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
private List<Surveillance> ListeSurveillance=new ArrayList<>();

@ManyToOne
private TypeExamen typeExamen;
@ManyToOne
private Semestre semestre;
@ManyToOne
private Session session;
@ManyToOne  
private ElementPedagogique elementP ;
public ElementPedagogique getElementP() {
	return elementP;
}
public void setElementP(ElementPedagogique elementP) {
	this.elementP = elementP;
}
}
