package com.ensah.service;

import java.util.List;

import com.ensah.bo.Administrateur;

public interface AdministrateurService {
	List<Administrateur> getAllAdministrateurs();
	
	Administrateur saveAdministrateur(Administrateur administrateur);
	
	Administrateur getAdministrateurById(Long id);
	
	Administrateur updateAdministrateur(Administrateur administrateur);
	
	void deleteAdministrateurById(Long id);

}
