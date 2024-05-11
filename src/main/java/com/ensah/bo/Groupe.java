package com.ensah.bo;
import java.util.List;

import jakarta.persistence.*;


public class Groupe {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 private String nom;
	 @OneToMany(mappedBy = "groupe")
	 private List<Enseignant> enseignants;
	 
	
	    
	    

}
