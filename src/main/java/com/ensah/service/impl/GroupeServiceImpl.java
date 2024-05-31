package com.ensah.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensah.bo.Groupe;
import com.ensah.repository.GroupeRepository;
import com.ensah.service.GroupeService;

import jakarta.transaction.Transactional;

@Service
public class GroupeServiceImpl implements GroupeService  {

    @Autowired
    private GroupeRepository groupeRepository;

    public List<Groupe> getAllGroupes() {
        return groupeRepository.findAll();
    }

    public Groupe getGroupeById(Long id) {
        return groupeRepository.findById(id).orElse(null);
    }

    public Groupe saveGroupe(Groupe groupe) {
        return groupeRepository.save(groupe);
    }

    public void deleteGroupeById(Long id) {
        Groupe groupe = groupeRepository.findById(id).orElse(null);
        if (groupe != null) {
            groupe.getListeEnseignants().forEach(enseignant -> enseignant.setGroupe(null));
            groupeRepository.deleteById(id);
        }
    }
    
    @Transactional
    public Groupe updateGroupe(Groupe groupe) {
        // Check if the groupe exists in the database
        Groupe existingGroupe = groupeRepository.findById(groupe.getIdGroupe()).orElse(null);
        if (existingGroupe != null) {
            // Update the attributes of the existing groupe
            existingGroupe.setNomGroupe(groupe.getNomGroupe());
            existingGroupe.setListeEnseignants(groupe.getListeEnseignants());
            // Save the updated groupe
            return groupeRepository.save(existingGroupe);
        }
        return null; // Or throw an exception if you want to handle non-existent group updates differently
    }
   
}

