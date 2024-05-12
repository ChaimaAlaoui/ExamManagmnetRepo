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
import com.ensah.bo.Enseignant;
import com.ensah.bo.Niveau;
import com.ensah.bo.TypeElement;
import com.ensah.repository.TypeElementRepository;
import com.ensah.repository.ElementspRepository;
import com.ensah.repository.NiveauRepository;
import com.ensah.repository.EnseignantRepository;

@Controller
public class ElementPedController {
	@Autowired
	 private ElementspRepository ElementspRepository;
	@Autowired
	 private TypeElementRepository TypeElementRepository;
	@Autowired
	 private NiveauRepository NiveauRepository;
	@Autowired
	 private EnseignantRepository EnseignantRepository;
	
	@GetMapping("/ElementsPedagogique")
	public String listEnseignants(Model model,@RequestParam(defaultValue = "0") int page) {
		int pageSize = 5;
	    Page<ElementPedagogique> elementPedagogique = ElementspRepository.findAll(PageRequest.of(page, pageSize));
		model.addAttribute("elementPedagogique",elementPedagogique);
		return "ElementsPedagogique";
	}
	@GetMapping("/AddElement")
	public String AddElement(Model model) {
		List<Niveau> Niveaux=NiveauRepository.findAll();
		List<TypeElement> TypeElements=TypeElementRepository.findAll();
		List<Enseignant> Enseignants=EnseignantRepository.findAll();
		model.addAttribute("Enseignants",Enseignants);
		model.addAttribute("Niveaux",Niveaux);
		model.addAttribute("TypeElements",TypeElements);
		return "AddElement";
	}
	@PostMapping("/AddElementSubmit")
	public String AddElementSubmit(String titre,Long IdType,Long IdNiveau,Long IdCoord,Long IdEns) {
		Enseignant enseignant = EnseignantRepository.findById(IdEns).orElseThrow();
		Enseignant coordinateur = EnseignantRepository.findById(IdCoord).orElseThrow();
		Niveau niveau = NiveauRepository.findById(IdNiveau).orElseThrow();
		TypeElement typeElement =TypeElementRepository.findById(IdType).orElseThrow();
		ElementPedagogique element = new ElementPedagogique();
		element.setEnseignantcoordonne(coordinateur);
		element.setEnseignantEnsiger(enseignant);
		element.setNiveau(niveau);
		element.setTypeElement(typeElement);
		element.setNomEpd(titre);
		ElementspRepository.save(element);
		return "redirect:/ElementsPedagogique";
	}
	
	@PostMapping("/DeleteElement")
	public String DeleteType (Long IdEpd) {
		System.out.print(IdEpd);
		ElementPedagogique e =	ElementspRepository.findById(IdEpd).orElseThrow();
		ElementspRepository.delete(e);
		
		return "redirect:/ElementsPedagogique";
	}
	@PostMapping("/UpdateElementSubmit")
	public String UpdateElementSubmit (Long IdEpd ,String titre,Long IdType,Long IdNiveau,Long IdCoord,Long IdEns) {
		ElementPedagogique element =ElementspRepository.findById(IdEpd).orElseThrow();
		element.setEnseignantEnsiger(EnseignantRepository.findById(IdEns).orElseThrow());
element.setEnseignantcoordonne(EnseignantRepository.findById(IdCoord).orElseThrow());
element.setNiveau(NiveauRepository.findById(IdNiveau).orElseThrow());
element.setTypeElement(TypeElementRepository.findById(IdType).orElseThrow());
element.setNomEpd(titre);
System.out.print(element.getIdEpd());
		ElementspRepository.save(element);
		
	
return "redirect:/ElementsPedagogique";
	}
	@PostMapping("/UpdateElement")
	public String UpdateElement (Model model ,Long IdEpd) {
		ElementPedagogique e =	ElementspRepository.findById(IdEpd).orElseThrow();
model.addAttribute("ElementPedagogique",e);
		
List<Niveau> Niveaux=NiveauRepository.findAll();
List<TypeElement> TypeElements=TypeElementRepository.findAll();
List<Enseignant> Enseignants=EnseignantRepository.findAll();
model.addAttribute("Enseignants",Enseignants);
model.addAttribute("Niveaux",Niveaux);
model.addAttribute("TypeElements",TypeElements);
		return "UpdateElement";
	}
}
