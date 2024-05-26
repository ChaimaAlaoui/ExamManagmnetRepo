package com.ensah.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ensah.bo.ElementPedagogique;
import com.ensah.bo.Niveau;
import com.ensah.repository.ElementspRepository;
import com.ensah.service.ElementService;
@Service
public class ElementServiceImpl implements ElementService {
	@Autowired
private ElementspRepository elementpRep;
	
	public List<ElementPedagogique> findByEnseignantId(Long enseignantId) {
        return elementpRep.findByEnseignantEnsiger_Id(enseignantId);
    }
	    
	@Override
	public List<ElementPedagogique> getAllElement() {
		return elementpRep.findAll();
		
	}

	@Override
	public ElementPedagogique saveElement(ElementPedagogique elementPedagogique) {
		return elementpRep.save(elementPedagogique);
		
	}

	@Override
	public ElementPedagogique getElementById(Long id) {
		return elementpRep.findById(id).get();
		 
	}

	@Override
	public ElementPedagogique updateElement(ElementPedagogique elementPedagogique) {
		return elementpRep.save(elementPedagogique);
	}

	@Override
	public void deleteElementById(Long id) {
		elementpRep.deleteById(id);		
	}

	@Override
	public boolean existsByNiveau(Niveau niveau) {
		return elementpRep.existsByNiveau(niveau);
		 	
	}

	@Override
	public List<ElementPedagogique> findAllByNiveau(Niveau niveau) {
		
		return elementpRep.findAllByNiveau(niveau); 
	}
	 

	@Override
	public org.hibernate.query.Page findByPage(java.awt.print.Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	

}