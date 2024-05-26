package com.ensah.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensah.bo.Enseignant;
import com.ensah.bo.Examen;
import com.ensah.repository.ExamenRepository;
import com.ensah.service.ExamenService;
@Service
public class ExamenServiceImpl implements ExamenService {
@Autowired
	private ExamenRepository ExamenRepository;
	
	
	@Override
	public List<Examen> getAllExamen() {
		return ExamenRepository.findAll();
	}

	@Override
	public Examen saveExamen(Examen Examen) {
		return ExamenRepository.save(Examen);
	}

	@Override
	public Examen getExamenById(Long id) {
		return ExamenRepository.findById(id).get();
	}

	@Override
	public Examen updateExamen(Examen Examen) {
		return ExamenRepository.save(Examen);
	}

	@Override
	public void deleteExamenById(Long id) {
		ExamenRepository.deleteById(id);	
	}

	@Override
	public List<Examen> getAllExamenByDate(Date date) {
		return ExamenRepository.findAllByDate(date);
	}



}
