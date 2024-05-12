package com.ensah.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ensah.bo.Enseignant;

@Service
public interface EnseignantService {
	
   List<Enseignant> getAllEnseignants();
	
   Enseignant saveEnseignant(Enseignant enseignant);
	
   Enseignant getEnseignantById(Long id);
	
   Enseignant updateEnseignant(Enseignant enseignant);
	
   void deleteEnseignantById(Long id);

}
