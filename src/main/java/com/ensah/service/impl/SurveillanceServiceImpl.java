package com.ensah.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensah.bo.Surveillance;
import com.ensah.repository.RepositorySurveillance;
import com.ensah.service.SurveillanceService;
@Service

public class SurveillanceServiceImpl implements  SurveillanceService {
@Autowired
private RepositorySurveillance repositorySurveillance;
	@Override
	public List<Surveillance> getAllSurveillances() {
		return repositorySurveillance.findAll() ;
	}

	@Override
	public Surveillance saveSurveillance(Surveillance surveillance) {
		
		return repositorySurveillance.save(surveillance);
	}

	@Override
	public Surveillance getSurveillanceById(Long id) {
		return repositorySurveillance.findById(id).get() ;
	}

	@Override
	public Surveillance updateSurveillance(Surveillance surveillance ) {
		return repositorySurveillance.save(surveillance) ;
	}

	@Override
	public void deleteSurveillanceById(Long id) {
		repositorySurveillance.deleteById(id);
		
	}

}