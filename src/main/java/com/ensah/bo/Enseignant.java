package com.ensah.bo;


import java.util.List;

import com.ensah.bo.ElementPedagogique;

import java.util.ArrayList;
import jakarta.persistence.*;


@Entity
@PrimaryKeyJoinColumn(name = "idEnseighant")
public class Enseignant extends Personnel {

	 private String specialite;
	 @OneToMany(mappedBy = "enseignantCoordonneSurveillance", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	 List<Surveillance> listeSurveillanceCoordonne=new ArrayList<>();
	 
	 @ManyToMany
		private List<Surveillance> listeSurveillanceSurveille=new ArrayList<>();
	 
	 @ManyToOne 
	 private Filiere filiere;
	 @ManyToOne 
	 private Departement departement;
	 
	
	 @ManyToOne
	 private Groupe groupe;
	
	  @OneToMany(mappedBy = "enseignantEnsiger", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	    List<ElementPedagogique> ListeElementsEpdEnsiger=new ArrayList<>();
	  
	 @OneToMany(mappedBy = "enseignantcoordonne", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	    List<ElementPedagogique> ListeElementsEpdCoordonne=new ArrayList<>();
	 
	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public List<Surveillance> getListeSurveillanceCoordonne() {
		return listeSurveillanceCoordonne;
	}

	public void setListeSurveillanceCoordonne(List<Surveillance> listeSurveillanceCoordonne) {
		this.listeSurveillanceCoordonne = listeSurveillanceCoordonne;
	}

	public List<Surveillance> getListeSurveillanceSurveille() {
		return listeSurveillanceSurveille;
	}

	public void setListeSurveillanceSurveille(List<Surveillance> listeSurveillanceSurveille) {
		this.listeSurveillanceSurveille = listeSurveillanceSurveille;
	}

	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
	// Méthode pour définir le nom du groupe directement depuis l'enseignant
    public void setNomGroupe(String nomGroupe) {
        if (this.groupe == null) {
            this.groupe = new Groupe(); // Créer un nouvel objet Groupe si nécessaire
        }
        this.groupe.setNomGroupe(nomGroupe);
    }


}