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

import com.ensah.bo.TypeElement;
import com.ensah.repository.TypeElementRepository;

@Controller
public class TypeElementController {
	@Autowired
	 private TypeElementRepository typeElementRepository;
	@GetMapping("/TypeElement")
	public String listEnseignants(Model model,@RequestParam(defaultValue = "0") int page) {
		int pageSize = 5;
	    Page<TypeElement> typeElementPage = typeElementRepository.findAll(PageRequest.of(page, pageSize));
		model.addAttribute("typeElementPage",typeElementPage);
		return "ListeTypeElement";
	}
	@GetMapping("/AddTypeElement")
	public String AddElement( ) {
		
		return "AddTypeElement";
	}
	@PostMapping("/AddTypeElementSubmit")
	public String AddTypeElementSubmit(String titre ) {
		typeElementRepository.save(new TypeElement(titre));
		 return "redirect:/TypeElement";
	}
	@PostMapping("/UpdateType")
	public String UpdateType (Model model ,Long IdType) {
		TypeElement e =	typeElementRepository.findById(IdType).orElseThrow();
model.addAttribute("TypeElement",e);
		
	
		return "UpdateType";
	}
	@PostMapping("/DeleteType")
	public String DeleteType (Long IdType) {
		System.out.print(IdType);
		TypeElement e =	typeElementRepository.findById(IdType).orElseThrow();
		typeElementRepository.delete(e);
		
		return "redirect:/TypeElement";
	}
	@PostMapping("/UpdateTypeElementSubmit")
	public String UpdateTypeElementSubmit (TypeElement e) {
		

typeElementRepository.save(e);
		
	
return "redirect:/TypeElement";
	}
	

}
