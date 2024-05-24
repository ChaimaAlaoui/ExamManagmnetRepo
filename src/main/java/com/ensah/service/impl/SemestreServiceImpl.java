package com.ensah.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensah.bo.Semestre;
import com.ensah.repository.SemestreRepository;
import com.ensah.service.SemestreService;
@Service
public class SemestreServiceImpl implements SemestreService {
@Autowired
private SemestreRepository semestreRepository;
	@Override
	public List<Semestre> getAllSemestre() {
		return semestreRepository.findAll();
	}

	@Override
	public Semestre getSemestreById(Long id) {
		return semestreRepository.findById(id).get();
	}

}
