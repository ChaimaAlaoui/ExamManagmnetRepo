package com.ensah.bo;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
public class Salle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long IdSalle;
	private String nomSalle ; 
	private Long Capacite ;
	@OneToMany(mappedBy = "salle", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Surveillance> ListeSurveillances= new ArrayList<>();
	public Long getIdSalle() {
		return IdSalle;
	}
	public void setIdSalle(Long idSalle) {
		IdSalle = idSalle;
	}
	public String getNomSalle() {
		return nomSalle;
	}
	public void setNomSalle(String nomSalle) {
		this.nomSalle = nomSalle;
	}
	public Long getCapacite() {
		return Capacite;
	}
	public void setCapacite(Long capacite) {
		Capacite = capacite;
	}
	public List<Surveillance> getListeSurveillances() {
		return ListeSurveillances;
	}
	public void setListeSurveillances(List<Surveillance> listeSurveillances) {
		ListeSurveillances = listeSurveillances;
	}
}
