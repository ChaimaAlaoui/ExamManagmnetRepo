package com.ensah.bo;
import java.util.List;

import jakarta.persistence.*;


public class Departement {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String nom;

	    @OneToMany(mappedBy = "departement")
	    private List<Enseignant> enseignants ;

}
