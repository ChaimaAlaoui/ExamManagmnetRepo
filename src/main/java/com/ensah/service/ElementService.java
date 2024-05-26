package com.ensah.service;

import java.awt.print.Pageable;
import java.util.List;

import org.hibernate.query.Page;

import com.ensah.bo.ElementPedagogique;
import com.ensah.bo.Niveau;

public interface ElementService {
	
    List<ElementPedagogique> findByEnseignantId(Long enseignantId);

	
	 List<ElementPedagogique> getAllElement();
		
	 ElementPedagogique saveElement(ElementPedagogique elementPedagogique);
		
	 ElementPedagogique getElementById(Long id);
		
	 ElementPedagogique updateElement(ElementPedagogique elementPedagogique);
		
	 void deleteElementById(Long id);
	   
	 boolean existsByNiveau(Niveau niveau);
	 List<ElementPedagogique> findAllByNiveau(Niveau niveau);
     Page findByPage(Pageable pageable);
}
