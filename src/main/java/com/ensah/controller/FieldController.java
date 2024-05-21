package com.ensah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ensah.bo.Filiere;
import com.ensah.service.FiliereService;

import java.util.List;

@Controller
public class FieldController {

	    @Autowired
	    private FiliereService filiereService;

	    @GetMapping("/filieres")
	    public String listFiliere(Model model) {
	        List<Filiere> filieres = filiereService.getAllFilieres();
	        model.addAttribute("filieres", filieres);
	        return "filiere-list"; //a creer !!!!!
	    }

	    @GetMapping("/filieres/{id}")
	    public String getFiliereById(@PathVariable Long id, Model model) {
	    	Filiere filiere = filiereService.getFiliereById(id);
	        model.addAttribute("filiere", filiere);
	        return "filiere-detail"; // a creer!!!!
	    }

	    @GetMapping("/filieres/add")
	    public String addFiliereForm(Model model) {
	    	
	        model.addAttribute("filiere", new Filiere());
	        return "add_filiere"; //a creer!!!!
	    }

	    @PostMapping("/filieres/add")
	    public String saveFiliere(@ModelAttribute Filiere filiere) {
	    	filiereService.saveFiliere(filiere);
	        return "redirect:/filieres";
	    }

	    @GetMapping("/filieres/edit/{id}")
	    public String editFiliereForm(@PathVariable Long id, Model model) {
	    	Filiere filiere = filiereService.getFiliereById(id);
	        model.addAttribute("filiere", filiere);
	        return "edit_filiere"; // a creer!!!
	    }

	    @PostMapping("/filieres/edit/{id}")
	    public String updateFiliere(@PathVariable Long id, @ModelAttribute Filiere filiere) {
	    	filiere.setIdFiliere(id);
	        filiereService.saveFiliere(filiere);
	        return "redirect:/filieres";
	    }

	    @GetMapping("/filieres/delete/{id}")
	    public String deleteFiliere(@PathVariable Long id) {
	    	filiereService.deleteFiliereById(id);
	        return "redirect:/filieres";
	    }
	

}


