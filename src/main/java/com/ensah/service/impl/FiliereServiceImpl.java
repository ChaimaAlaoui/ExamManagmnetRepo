package com.ensah.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensah.bo.Filiere;
import com.ensah.repository.FiliereRepository;
import com.ensah.service.FiliereService;

import jakarta.transaction.Transactional;

@Service
public class FiliereServiceImpl implements FiliereService  {

    @Autowired
    private FiliereRepository filiereRepository;

    public List<Filiere> getAllFilieres() {
        return filiereRepository.findAll();
    }

    public Filiere getFiliereById(Long id) {
        return filiereRepository.findById(id).orElse(null);
    }

    public Filiere saveFiliere(Filiere filiere) {
        return filiereRepository.save(filiere);
    }

    public void deleteFiliereById(Long id) {
    	Filiere filiere = filiereRepository.findById(id).orElse(null);
        if (filiere != null) {
        	filiere.getListeEnseignants().forEach(enseignant -> enseignant.setFiliere(null));
        	filiereRepository.deleteById(id);
        }
    }
    
    @Transactional
    public Filiere updateFiliere(Filiere filiere) {
        // Check if the groupe exists in the database
    	Filiere existingFiliere = filiereRepository.findById(filiere.getIdFiliere()).orElse(null);
        if (existingFiliere != null) {
            // Update the attributes of the existing groupe
        	existingFiliere.setNomFiliere(filiere.getNomFiliere());
        	existingFiliere.setListeEnseignants(filiere.getListeEnseignants());
            // Save the updated groupe
            return filiereRepository.save(existingFiliere);
        }
        return null; // Or throw an exception if you want to handle non-existent group updates differently
    }
}



