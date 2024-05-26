package com.ensah.service;

import java.util.List;

import com.ensah.bo.Surveillance;



public interface SurveillanceService {
	
	 List<Surveillance> getAllSurveillances();
		
	 Surveillance saveSurveillance(Surveillance surveillance);
	
	 Surveillance getSurveillanceById(Long id);
 	
	 Surveillance updateSurveillance(Surveillance groupe);
	
	 void deleteSurveillanceById(Long id);
}