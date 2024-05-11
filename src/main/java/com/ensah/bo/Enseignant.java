package com.ensah.bo;
import jakarta.persistence.*;

@Entity
@Table(name ="Enseignant")
public class Enseignant extends Personnel{
	
	@ManyToOne
    @JoinColumn(name = "groupe_id")
    private Groupe groupe;
	
	 @ManyToOne
	 @JoinColumn(name = "departement_id")
	 private Departement departement;
	 
	 @ManyToOne
	 @JoinColumn(name = "filiere_id")
	 private Filière filiere;
	 
	
	
}
