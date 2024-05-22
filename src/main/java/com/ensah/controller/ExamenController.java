package com.ensah.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ensah.bo.Salle;
import com.ensah.service.SalleService;
import com.ensah.service.impl.ElementServiceImpl;

@Controller 
public class ExamenController {
	@Autowired
	 private ElementServiceImpl elementServiceImpl;
	@Autowired
private SalleService salleService;
	@GetMapping("/ListeExamens")
	public String  ListeExamens () {
		
		return "ListeExamens";
	}
	@GetMapping("/AddExamen")
	public String  AddExamen (Model model) {
		model.addAttribute("listeModules",elementServiceImpl.getAllElement());
		model.addAttribute("listeSalles",salleService.getAllSalle());

		return "AddExamen";
	}
	@PostMapping("/SubmitExam")
	public String  SubmitExam (@RequestParam List<String>teacherCount,@RequestParam List<String>salles) {
		
		String[] parts = salles.get(0).split("-");
        
        int computedIndex = Integer.parseInt(parts[1]);
        System.out.println(computedIndex);    
		    
		return "AddExamen";
	}
	
	
}
