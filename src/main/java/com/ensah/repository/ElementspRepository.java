package com.ensah.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensah.bo.ElementPedagogique;
import com.ensah.bo.Enseignant;
import com.ensah.bo.Niveau;
@Repository
public interface ElementspRepository extends JpaRepository<ElementPedagogique, Long>  {
	  boolean existsByNiveau(Niveau niveau);
	  List<ElementPedagogique> findAllByNiveau(Niveau niveau);
}
