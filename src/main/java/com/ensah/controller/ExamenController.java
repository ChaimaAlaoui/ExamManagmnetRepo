package com.ensah.controller;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.sql.Time;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
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

import com.ensah.Algorithme.TimeConverter;
import com.ensah.bo.Enseignant;
import com.ensah.bo.Salle;
import com.ensah.bo.Examen;
import com.ensah.bo.Surveillance;
import com.ensah.service.ElementService;
import com.ensah.service.EnseignantService;
import com.ensah.service.ExamenService;
import com.ensah.service.Human_ResourcesService;
import com.ensah.service.SalleService;
import com.ensah.service.SemestreService;
import com.ensah.service.SessionService;
import com.ensah.service.SurveillanceService;
import com.ensah.service.TypeExamenService;
import com.ensah.service.impl.ElementServiceImpl;

@Controller 
public class ExamenController {
	@Autowired 
	private ExamenService examenService;
	@Autowired 
	private SemestreService semestreService;
	@Autowired
	private SessionService sessionServiceimpl;
	@Autowired 
	private SurveillanceService SurveillanceServiceImpl;
	@Autowired
	 private ElementService elementServiceImpl;
	@Autowired
	 private EnseignantService EnseignantServiceImpl;
	@Autowired
	 private TypeExamenService TypeExamenImpl;
	@Autowired
private SalleService salleService;
	@Autowired 
	private Human_ResourcesService Human_ResourcesServiceImpl;
	@GetMapping("/ListeExamens")
	public String  ListeExamens (Model model) {
		model.addAttribute("ListeExamen",examenService.getAllExamen());

		return "ListeExamens";
	}
	@GetMapping("/AddExamen")
	public String  AddExamen (Model model) {
		model.addAttribute("listeModules",elementServiceImpl.getAllElement());
		model.addAttribute("listeSalles",salleService.getAllSalle());
		model.addAttribute("listeSession",sessionServiceimpl.getAllSession());
		model.addAttribute("listeSemestre",semestreService.getAllSemestre());
		model.addAttribute("listeTypeExamen",TypeExamenImpl.getAllTypeExamen());
		return "AddExamen";
	}
	@PostMapping("/SubmitExam")
	public String  SubmitExam (@RequestParam List<String>teacherCount,@RequestParam(name = "salles", required = false) List<String>salles,@RequestParam String NomExamen,@RequestParam String elementPed,@RequestParam String session,@RequestParam String method,@RequestParam String  DateExam,@RequestParam String  HeurExam,@RequestParam String  DureeExam,@RequestParam String rapport,@RequestParam String semestre,@RequestParam String TypeExamen,RedirectAttributes redirectAttributes) {
		if(salles==null) {redirectAttributes.addFlashAttribute("error", "Veuillez choisir une salle.");
    	
   	 return "redirect:/AddExamen";}
		
		Examen  exam =new Examen();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date DateExamen;
	    try {
	        Date parsedDate = dateFormat.parse(DateExam);
	        DateExamen=parsedDate;
	        exam.setDate(parsedDate);
	    } catch (ParseException e) {
	        e.printStackTrace();
	        redirectAttributes.addFlashAttribute("error", "Format de date invalide.");
	        return "redirect:/AddExamen";
	    }
	    float ExamIntervalStart =TimeConverter.convertTimeToFloat(HeurExam);
	    LocalTime time = LocalTime.parse(HeurExam);
	   
		int Duration = Integer.parseInt(DureeExam) ;
		 LocalTime updatedTime = time.plusMinutes(Duration);
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	        String formattedTime = updatedTime.format(formatter);
		    float ExamIntervalEnd =TimeConverter.convertTimeToFloat(formattedTime);

		
		List<Enseignant> ListeEnseignants=Human_ResourcesServiceImpl.getAvailableTeachers(DateExamen, ExamIntervalStart, ExamIntervalEnd);
		Set<Salle> ListeUnavailableSalles=Human_ResourcesServiceImpl.getUnavailableSalles(DateExamen, ExamIntervalStart, ExamIntervalEnd);

		List<Surveillance> listeSurveillance=new ArrayList<>();
        Random rand = new Random();
if(ListeEnseignants.size()<1) {redirectAttributes.addFlashAttribute("error", "Le nombre de surveillants est supérieur au nombre d'enseignants.");
return "redirect:/AddExamen";}
		  int index1 = rand.nextInt(ListeEnseignants.size());
		  Enseignant coordinateur=ListeEnseignants.get(index1);
	       
	       	ListeEnseignants.remove(index1);
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
	        List <Enseignant> listEnseignantSurveillance = new ArrayList<>();
	        for (int j =0;j<NumberSurveillance;j++) {
	        	
	        	
	        	int index = rand.nextInt(ListeEnseignants.size());
	        	listEnseignantSurveillance.add(ListeEnseignants.get(index));
	        	
	        	ListeEnseignants.remove(index);
	        }
	        
			Long idSalleSur = Long.valueOf(IdSalle); 
			s.setEnseignantCoordonneSurveillance(coordinateur);
s.setEnseignantSurveillanceList(listEnseignantSurveillance);
if(ListeUnavailableSalles.contains(salleService.getSalleById(idSalleSur))) {
	StringBuilder errorMessage = new StringBuilder("The following classRooms are not available in this date " + DateExam + " and this interval " + HeurExam+"to"+updatedTime + ": ");

	for (Salle salle : ListeUnavailableSalles) {
	    errorMessage.append(salle.getNomSalle()).append(", ");
	}

	errorMessage.setLength(errorMessage.length() - 2);
	 redirectAttributes.addFlashAttribute("error",errorMessage);
	 return "redirect:/AddExamen";
}
	       s.setSalle(salleService.getSalleById(idSalleSur));
	     
	       
        s.setExamen(exam);
	       listeSurveillance.add(s);
	        System.out.println("surv"+i+"liste :"+s.getEnseignantSurveillanceList());    
			
		}
		exam.setTitreExamen(NomExamen);
		
		Long elementId = Long.valueOf(Integer.parseInt(elementPed)); 
		exam.setElementP(elementServiceImpl.getElementById(elementId));
		Long elementSession = Long.valueOf(Integer.parseInt(session)); 
		exam.setSession(sessionServiceimpl.getSessionById(elementSession));
		exam.setHeur(HeurExam);
		    try {
		        Date parsedDate = dateFormat.parse(DateExam);
		        exam.setDate(parsedDate);
		    } catch (ParseException e) {
		        e.printStackTrace();
		        redirectAttributes.addFlashAttribute("error", "Format de date invalide.");
		        return "redirect:/AddExamen";
		    }
		exam.setDurationMinutes(Integer.parseInt(DureeExam));
		exam.setRealDurationMinutes(Integer.parseInt(DureeExam));
		Long elementsemestre = Long.valueOf(Integer.parseInt(semestre));
		
		Long TypeExamenId = Long.valueOf(Integer.parseInt(TypeExamen));
		exam.setTypeExamen(TypeExamenImpl.getTypeExamenById(TypeExamenId));

		exam.setSemestre(semestreService.getSemestreById(elementsemestre));
		exam.setListeSurveillance(listeSurveillance);
		examenService.saveExamen(exam);
        System.out.println("\n le titre "+NomExamen);    
        System.out.println("\n l'element pedagogique "+elementPed);  
        System.out.println("\n Session "+session); 
        System.out.println("\n Method "+method); 
        System.out.println("\n DateExam "+DateExam); 
        System.out.println("\n HeurExam "+HeurExam); 
        System.out.println("\n DureeExam "+DureeExam); 
        System.out.println("\n rapport "+rapport); 
		return "redirect:/ListeExamens";
	}
	
	
}
