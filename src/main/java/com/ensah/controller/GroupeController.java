package com.ensah.controller;

import com.ensah.bo.Groupe;
import com.ensah.service.GroupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GroupeController {

	    @Autowired
	    private GroupeService groupeService;

	    @GetMapping("/groupes")
	    public String listGroupes(Model model) {
	        List<Groupe> groupes = groupeService.getAllGroupes();
	        model.addAttribute("groupes", groupes);
	        return "groupe-list"; // Make sure you have a Thymeleaf template named 'groupe-list.html'
	    }

	    @GetMapping("/{id}")
	    public String getGroupeById(@PathVariable Long id, Model model) {
	        Groupe groupe = groupeService.getGroupeById(id);
	        model.addAttribute("groupe", groupe);
	        return "groupe-detail"; // Make sure you have a Thymeleaf template named 'groupe-detail.html'
	    }

	    @GetMapping("/groupes/add")
	    public String addGroupeForm(Model model) {
	    	
	        model.addAttribute("groupe", new Groupe());
	        return "add_groupe"; // Make sure you have a Thymeleaf template named 'groupe-add.html'
	    }

	    @PostMapping("/groupes/add")
	    public String saveGroupe(@ModelAttribute Groupe groupe) {
	        groupeService.saveGroupe(groupe);
	        return "redirect:/groupes";
	    }

	    @GetMapping("/groupes/edit/{id}")
	    public String editGroupeForm(@PathVariable Long id, Model model) {
	        Groupe groupe = groupeService.getGroupeById(id);
	        model.addAttribute("groupe", groupe);
	        return "edit_groupe"; // Make sure you have a Thymeleaf template named 'groupe-edit.html'
	    }

	    @PostMapping("/groupes/edit/{id}")
	    public String updateGroupe(@PathVariable Long id, @ModelAttribute Groupe groupe) {
	        groupe.setIdGroupe(id);
	        groupeService.saveGroupe(groupe);
	        return "redirect:/groupes";
	    }

	    @GetMapping("/groupes/delete/{id}")
	    public String deleteGroupe(@PathVariable Long id) {
	        groupeService.deleteGroupeById(id);
	        return "redirect:/groupes";
	    }
	

}

