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

import com.ensah.bo.Administrateur;

import com.ensah.service.AdministrateurService;



@Controller
public class AbsenceController {


	private AdministrateurService administrateurService;

	public AbsenceController(AdministrateurService administrateurService) {
		super();
		this.administrateurService = administrateurService;
	}
	
	// handler method to handle list admin and return mode and view
	@GetMapping("/admin")
	public String listEnseignants(Model model) {
		model.addAttribute("controleursAbsence", administrateurService.getAllAdministrateurs());
		return "ControleursAbsence";
	}
	

	@GetMapping("/admin/new")
	public String createEnseignantForm(Model model) {
		
	    
		Administrateur administrateur= new Administrateur ();
	    

	    model.addAttribute("administrateur", administrateur);
	    return "create_admin";
	}
	
	@PostMapping("/admin")
	public String saveEnseignant(@ModelAttribute("administrateur") Administrateur administrateur) {
	   
	    

		administrateurService.saveAdministrateur(administrateur);

	    return "redirect:/admin";
	}


	
	@GetMapping("/admin/edit/{id}")
	public String editAdminForm(@PathVariable Long id, Model model) {
	    // Fetch the admin object by its ID
		Administrateur administrateur = administrateurService.getAdministrateurById(id);

	    // Add the fetched data to the model
	    model.addAttribute("administrateur", administrateur);
	 

	    // Return the view name
	    return "edit_admin";
	}

//apres le submit btn cette methode est appelee
	@PostMapping("/admin/{id}")
	public String updateAdmin(@PathVariable Long id,
			@ModelAttribute("administrateur") Administrateur administrateur,Model model) {
		
		// get admin from database by id
		Administrateur existingAdmin = administrateurService.getAdministrateurById(id);
		existingAdmin.setId(id);
		existingAdmin.setFirstName(administrateur.getFirstName());
		existingAdmin.setLastName(administrateur.getLastName());
		existingAdmin.setEmail(administrateur.getEmail());
		existingAdmin.setSeuil(administrateur.getSeuil());
		
		
		// save updated admin object
		administrateurService.updateAdministrateur(existingAdmin);
		return "redirect:/admin";		
	}
	
	// handler method to handle delete admin request
	
	@GetMapping("/admin/{id}")
	public String deleteEnseignant(@PathVariable Long id) {
		administrateurService.deleteAdministrateurById(id);
		return "redirect:/admin";
	}
	
	
	
    

	

}