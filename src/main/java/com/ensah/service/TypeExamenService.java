package com.ensah.service;

import java.util.List;

import com.ensah.bo.TypeExamen;



public interface TypeExamenService {
	 List<TypeExamen> getAllTypeExamen();
		
	 
	
	 TypeExamen getTypeExamenById(Long id);
 	
	
	
	
}
