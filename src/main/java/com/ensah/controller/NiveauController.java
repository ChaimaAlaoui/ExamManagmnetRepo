package com.ensah.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ensah.bo.ElementPedagogique;
import com.ensah.bo.Niveau;
import com.ensah.bo.TypeElement;
import com.ensah.repository.ElementspRepository;
import com.ensah.repository.NiveauRepository;
@Controller
public class NiveauController {
	@Autowired
	 private NiveauRepository NiveauRepository;
	@Autowired
	 private ElementspRepository ElementspRepository;
	@GetMapping("/Niveau")
	public String listEnseignants(Model model,@RequestParam(defaultValue = "0") int page) {
		int pageSize = 5;
	    Page<Niveau> NiveauPage = NiveauRepository.findAll(PageRequest.of(page, pageSize));
		model.addAttribute("NiveauPage",NiveauPage);
		return "Niveau";
	}
	@GetMapping("/AddNiveau")
	public String AddNiveau( ) {
		
		return "AddNiveau";
	}
	@PostMapping("/AddNiveauSubmit")
	public String AddNiveauSubmit(String titre ) {
		NiveauRepository.save(new Niveau(titre));
		 return "redirect:/Niveau";
	}
	@PostMapping("/UpdateNiveau")
	public String UpdateNiveau (Model model ,Long IdNiveau) {
		Niveau e =	NiveauRepository.findById(IdNiveau).orElseThrow();
model.addAttribute("Niveau",e);
		
	
		return "UpdateNiveau";
	}
	@PostMapping("/DeleteNiveau")
	public String DeleteNiveau (Model model ,Long IdNiveau) {
		System.out.print(IdNiveau);
		Niveau e =	NiveauRepository.findById(IdNiveau).orElseThrow();
		if(ElementspRepository.existsByNiveau(e)) {
			 List<ElementPedagogique> listeElement=ElementspRepository.findAllByNiveau(e);
			 for(ElementPedagogique i : listeElement) {
				 ElementspRepository.delete(i);
			 }
			
		}
		NiveauRepository.delete(e);
		
		return "redirect:/Niveau";
	}
	@PostMapping("/UpdateNiveauSubmit")
	public String UpdateNiveauSubmit (Niveau e) {
		

		NiveauRepository.save(e);
		
	
return "redirect:/Niveau";
	}
	
}
