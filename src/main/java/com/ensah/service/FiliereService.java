package com.ensah.service;



import java.util.List;

import com.ensah.bo.Filiere;

public interface FiliereService {
	
     List<Filiere> getAllFilieres();
	
     Filiere saveFiliere(Filiere filiere);
	
     Filiere getFiliereById(Long id);
 	
     Filiere updateFiliere(Filiere filiere);
	
	 void deleteFiliereById(Long id);

}

