package com.ensah.bo;
import java.util.List;

import jakarta.persistence.*;


public class Filière {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String nom;
	    
        @OneToMany(mappedBy = "filiere")
	    private List<Enseignant> enseignants ;


}
