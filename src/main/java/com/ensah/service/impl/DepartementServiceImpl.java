package com.ensah.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensah.bo.Departement;
import com.ensah.repository.DepartementRepository;
import com.ensah.service.DepartementService;

@Service
public class DepartementServiceImpl implements DepartementService {

    @Autowired
    private DepartementRepository departementRepository;

    
    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    
    public Departement getDepartementById(Long id) {
        return departementRepository.findById(id).orElse(null);
    }

    
    public Departement saveDepartment(Departement departement) {
        return departementRepository.save(departement);
    }

    
    public Departement updateDepartement(Departement departement) {
        Departement existingDepartement = departementRepository.findById(departement.getIdDepartement()).orElse(null);
        if (existingDepartement != null) {
            existingDepartement.setNomDepartement(departement.getNomDepartement());
            existingDepartement.setListeEnseignants(departement.getListeEnseignants());
            return departementRepository.save(existingDepartement);
        }
        return null;
    }

    
    public void deleteDepartementById(Long id) {
        Departement departement = departementRepository.findById(id).orElse(null);
        if (departement != null) {
            departement.getListeEnseignants().forEach(enseignant -> enseignant.setDepartement(null));
            departementRepository.deleteById(id);
        }
    }
}