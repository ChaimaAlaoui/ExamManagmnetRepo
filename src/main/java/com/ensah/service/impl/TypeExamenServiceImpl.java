package com.ensah.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ensah.repository.TypeExamenRepository;

import com.ensah.bo.TypeExamen;
import com.ensah.service.TypeExamenService;
@Service
public class TypeExamenServiceImpl implements TypeExamenService {
@Autowired 
private TypeExamenRepository  typeExamenRepository;
 
	@Override
	public List<TypeExamen> getAllTypeExamen() {
		return typeExamenRepository.findAll();
	}

	@Override
	public TypeExamen getTypeExamenById(Long id) {
		return typeExamenRepository.findById(id).get();
	}

}
