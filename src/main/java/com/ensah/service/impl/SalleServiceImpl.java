package com.ensah.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensah.bo.Salle;
import com.ensah.repository.SalleRepository;
import com.ensah.service.SalleService;
@Service

public class SalleServiceImpl implements SalleService {
	@Autowired
private SalleRepository salleRepository;

@Override
public List<Salle> getAllSalle() {
	
	return salleRepository.findAll();
}

}
