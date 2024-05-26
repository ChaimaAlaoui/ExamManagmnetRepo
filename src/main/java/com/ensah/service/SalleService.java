package com.ensah.service;

import java.util.List;

import com.ensah.bo.Salle;
import com.ensah.bo.Session;

public interface SalleService {
	List<Salle> getAllSalle();
	 Salle getSalleById(Long id);

	

}