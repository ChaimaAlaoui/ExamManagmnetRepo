package com.ensah.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ensah.bo.ElementPedagogique;
import com.ensah.bo.Enseignant;
import com.ensah.bo.Niveau;

public interface ElementService {
	 List<ElementPedagogique> getAllElement();
		
	 ElementPedagogique saveElement(ElementPedagogique elementPedagogique);
		
	 ElementPedagogique getElementById(Long id);
		
	 ElementPedagogique updateElement(ElementPedagogique elementPedagogique);
		
	   void deleteElementById(Long id);
	   boolean existsByNiveau(Niveau niveau);
		  List<ElementPedagogique> findAllByNiveau(Niveau niveau);
		    Page<ElementPedagogique> findByPage(Pageable pageable);


}
