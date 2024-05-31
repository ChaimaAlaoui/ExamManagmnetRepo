package com.ensah.service;

import java.util.Date;
import java.util.List;

import com.ensah.bo.Examen;

public interface ExamenService {
	List<Examen> getAllExamen();
	List<Examen> getAllExamenByDate(Date date);
	List<Examen>  getAllExamenByAnnee(String  Annee);
	Examen saveExamen(Examen examen);
		
	Examen getExamenById(Long id);
		
	Examen updateExamen(Examen examen);
		
	   void deleteExamenById(Long id);
}