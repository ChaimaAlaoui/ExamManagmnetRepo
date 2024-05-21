package com.ensah.service;

import java.util.List;

import com.ensah.bo.Departement;



public interface DepartementService {
	
	List<Departement> getAllDepartements();
	
	Departement getDepartementById(Long id);
	
	Departement saveDepartment(Departement departement);
	
	Departement updateDepartement(Departement departement);
	
	void deleteDepartementById(Long id);
}

