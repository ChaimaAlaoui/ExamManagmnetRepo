package com.ensah.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ensah.bo.Departement;
import com.ensah.bo.Enseignant;
import com.ensah.bo.Filiere;
import com.ensah.bo.Groupe;
import com.ensah.bo.Niveau;
import com.ensah.repository.GroupeRepository;
import com.ensah.service.DepartementService;
import com.ensah.service.EnseignantService;
import com.ensah.service.FiliereService;
import com.ensah.service.GroupeService;


@Controller
public class EnseignantController {
	@Autowired
	GroupeRepository groupeRepository;
	@Autowired
	GroupeService groupeService;
	@Autowired
	DepartementService departmentService;
	@Autowired
	FiliereService filiereService;

	private EnseignantService enseignantService;

	public EnseignantController(EnseignantService enseignantService) {
		super();
		this.enseignantService = enseignantService;
	}
	
	// handler method to handle list enseignants and return mode and view
	@GetMapping("/enseignants")
	public String listEnseignants(Model model) {
		model.addAttribute("enseignants", enseignantService.getAllEnseignants());
		return "test";
	}
	

	@GetMapping("/enseignants/new")
	public String createEnseignantForm(Model model) {
	    List<Groupe> groupes = groupeService.getAllGroupes();
	    List<Departement> departments = departmentService.getAllDepartements();
	    List<Filiere> filieres = filiereService.getAllFilieres();
	    
	    Enseignant enseignant= new Enseignant ();
	    
	    model.addAttribute("groupes", groupes);
	    model.addAttribute("departments", departments);
	    model.addAttribute("filieres", filieres);

	    model.addAttribute("enseignant", enseignant);
	    return "create_enseignant";
	}
	
	@PostMapping("/enseignants")
	public String saveEnseignant(@ModelAttribute("enseignant") Enseignant enseignant, Long idGroupe,Long idDepartement,Long idFiliere) {
	    Groupe groupe = groupeService.getGroupeById(idGroupe);
	    Departement departement=departmentService.getDepartementById(idDepartement);
	    Filiere filiere=filiereService.getFiliereById(idFiliere);
	    
	    enseignant.setGroupe(groupe);
	    enseignant.setDepartement(departement);
	    enseignant.setFiliere(filiere);
	    

	    enseignantService.saveEnseignant(enseignant);

	    return "redirect:/enseignants";
	}


	
	@GetMapping("/enseignants/edit/{id}")
	public String editEnseignantForm(@PathVariable Long id, Model model) {
	    // Fetch the enseignant object by its ID
	    Enseignant enseignant = enseignantService.getEnseignantById(id);

	    // Fetch the list of departments and groups
	    List<Departement> departments = departmentService.getAllDepartements();
	    List<Groupe> groupes = groupeService.getAllGroupes();
	    List<Filiere> filieres = filiereService.getAllFilieres();

	    // Add the fetched data to the model
	    model.addAttribute("enseignant", enseignant);
	    model.addAttribute("departments", departments);
	    model.addAttribute("groupes", groupes);
	    model.addAttribute("filieres", filieres);


	    // Return the view name
	    return "edit_enseignants";
	}

//apres le submit btn cette methode est appelee
	@PostMapping("/enseignants/{id}")
	public String updateEnseignant(@PathVariable Long id,
			@ModelAttribute("enseignant") Enseignant enseignant,Long idGroupe,Long idDepartement,Long idFiliere,
			Model model) {
		
		// get student from database by id
		Enseignant existingEnseignant = enseignantService.getEnseignantById(id);
		existingEnseignant.setId(id);
		existingEnseignant.setFirstName(enseignant.getFirstName());
		existingEnseignant.setLastName(enseignant.getLastName());
		existingEnseignant.setEmail(enseignant.getEmail());
		
		 Groupe groupe = groupeService.getGroupeById(idGroupe);
		 Departement departement=departmentService.getDepartementById(idDepartement);
		    
	    existingEnseignant.setDepartement(departement);
	    existingEnseignant.setGroupe(groupe);
	    
		 Filiere filiere = filiereService.getFiliereById(idFiliere);
		 existingEnseignant.setFiliere(filiere);




		
		
		// save updated student object
		enseignantService.updateEnseignant(existingEnseignant);
		return "redirect:/enseignants";		
	}
	
	// handler method to handle delete student request
	
	@GetMapping("/enseignants/{id}")
	public String deleteEnseignant(@PathVariable Long id) {
		enseignantService.deleteEnseignantById(id);
		return "redirect:/enseignants";
	}
	

}