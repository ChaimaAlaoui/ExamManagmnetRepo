package com.ensah.service;

import java.util.List;

import com.ensah.bo.Departement;

public interface DepartementService {
	
	 List<Departement> getAllDepartements();
		
	 Departement saveGroupe(Departement departement);
	
	 Departement getDepartementById(Long id);
 	
	 Departement updateDepartement(Departement departement);
	
	 void deleteDepartementById(Long id);

}
