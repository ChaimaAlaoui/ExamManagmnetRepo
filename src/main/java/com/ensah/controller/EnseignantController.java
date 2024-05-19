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

import com.ensah.bo.Enseignant;
import com.ensah.bo.Groupe;
import com.ensah.bo.Niveau;
import com.ensah.repository.GroupeRepository;
import com.ensah.service.EnseignantService;
import com.ensah.service.GroupeService;


@Controller
public class EnseignantController {
	@Autowired
	GroupeRepository groupeRepository;
	@Autowired
	GroupeService groupeService;

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
	    Enseignant enseignant= new Enseignant ();
	    model.addAttribute("groupes", groupes);
	    model.addAttribute("enseignant", enseignant);
	    return "create_enseignant";
	}
	
	@PostMapping("/enseignants")
	public String saveEnseignant(@ModelAttribute("enseignant") Enseignant enseignant, @RequestParam("idGroupe") Long idGroupe) {
	    Groupe groupe = groupeService.getGroupeById(idGroupe);
	    enseignant.setGroupe(groupe);

	    enseignantService.saveEnseignant(enseignant);

	    return "redirect:/enseignants";
	}


	
	@GetMapping("/enseignants/edit/{id}")
	public String editEnseignantForm(@PathVariable Long id, Model model) {
		model.addAttribute("enseignant", enseignantService.getEnseignantById(id));
		return "edit_enseignants";
	}
//apres le submit btn cette methode est appelee
	@PostMapping("/enseignants/{id}")
	public String updateEnseignant(@PathVariable Long id,
			@ModelAttribute("enseignant") Enseignant enseignant,
			Model model) {
		
		// get student from database by id
		Enseignant existingEnseignant = enseignantService.getEnseignantById(id);
		existingEnseignant.setId(id);
		existingEnseignant.setFirstName(enseignant.getFirstName());
		existingEnseignant.setLastName(enseignant.getLastName());
		existingEnseignant.setEmail(enseignant.getEmail());
		
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