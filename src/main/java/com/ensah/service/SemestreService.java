package com.ensah.service;

import java.util.List;

import com.ensah.bo.Semestre;


public interface SemestreService {
	List<Semestre> getAllSemestre();
	Semestre getSemestreById(Long id);
}
