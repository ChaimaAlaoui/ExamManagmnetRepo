package com.ensah.controller;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ensah.bo.Enseignant;
import com.ensah.bo.Salle;
import com.ensah.bo.Surveillance;
import com.ensah.service.ElementService;
import com.ensah.service.EnseignantService;
import com.ensah.service.SalleService;
import com.ensah.service.impl.ElementServiceImpl;

@Controller 
public class ExamenController {
	@Autowired
	 private ElementService elementServiceImpl;
	@Autowired
	 private EnseignantService EnseignantServiceImpl;
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
	public String  SubmitExam (@RequestParam List<String>teacherCount,@RequestParam(name = "salles", required = false) List<String>salles,@RequestParam String NomExamen,@RequestParam String elementPed,@RequestParam String session,@RequestParam String method,@RequestParam String  DateExam,@RequestParam String  HeurExam,@RequestParam String  DureeExam,@RequestParam String rapport,RedirectAttributes redirectAttributes) {
		if(salles==null) {redirectAttributes.addFlashAttribute("error", "Veuillez choisir une salle.");
    	
   	 return "redirect:/AddExamen";}
		List<Enseignant> ListeEnseignants=EnseignantServiceImpl.getAllEnseignants();
		for (int  i = 0 ; i<salles.size();i++) {
			
			String[] parts = salles.get(i).split("-");
			int IdSalle = Integer.parseInt(parts[0]);
	        int computedIndex = Integer.parseInt(parts[1]);
	        int NumberSurveillance= Integer.parseInt(teacherCount.get(computedIndex));
	        if(ListeEnseignants.size()<NumberSurveillance) {
	            redirectAttributes.addFlashAttribute("error", "Le nombre de surveillants est supérieur au nombre d'enseignants.");
	        	 return "redirect:/AddExamen";
	        }
	        Surveillance s =new Surveillance() ;
	        for (int j =0;j<NumberSurveillance;j++) {
	        	Random rand = new Random();
	        	
	        	int index = rand.nextInt(ListeEnseignants.size());
	        	s.addEnseignant(ListeEnseignants.get(index));
	        	ListeEnseignants.remove(index);
	        }
	        System.out.println("surv"+i+"liste :"+s.getEnseignantSurveillanceList());    
			
		}
        System.out.println("\n le titre "+NomExamen);    
        System.out.println("\n l'element pedagogique "+elementPed);  
        System.out.println("\n Session "+session); 
        System.out.println("\n Method "+method); 
        System.out.println("\n DateExam "+DateExam); 
        System.out.println("\n HeurExam "+HeurExam); 
        System.out.println("\n DureeExam "+DureeExam); 
        System.out.println("\n rapport "+rapport); 
		return "AddExamen";
	}
	
	
}
