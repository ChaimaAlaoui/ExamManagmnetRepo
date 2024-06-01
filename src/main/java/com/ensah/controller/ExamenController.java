package com.ensah.controller;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ensah.Algorithme.TimeConverter;
import com.ensah.bo.Administrateur;
import com.ensah.bo.Enseignant;
import com.ensah.bo.Salle;
import com.ensah.bo.Examen;
import com.ensah.bo.Groupe;
import com.ensah.bo.Niveau;
import com.ensah.bo.Surveillance;
import com.ensah.service.ElementService;
import com.ensah.service.EnseignantService;
import com.ensah.service.ExamenService;
import com.ensah.service.GroupeService;
import com.ensah.service.Human_ResourcesService;
import com.ensah.service.SalleService;
import com.ensah.service.SemestreService;
import com.ensah.service.SessionService;
import com.ensah.service.SurveillanceService;
import com.ensah.service.TypeExamenService;
import com.ensah.service.impl.ElementServiceImpl;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

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
	 private GroupeService GroupeServiceImpl;
	@Autowired
private SalleService salleService;
	@Autowired 
	private Human_ResourcesService Human_ResourcesServiceImpl;
	@GetMapping("/ListeExamens")
	public String  ListeExamens (Model model) {
		model.addAttribute("ListeExamen",examenService.getAllExamen());

		return "ListeExamens";
	}
	@PostMapping("/Examen_info/{id}")
	public String  ListeExamens (@PathVariable Long id ,Model model) {
		Examen exam=examenService.getExamenById(id);
        model.addAttribute("exam", exam);

		return "Examen_info";
	}

	 @GetMapping("/downloadPv/{id}")
	    public ResponseEntity<Resource> downloadFilePv(@PathVariable Long id) {
	        Examen exam = examenService.getExamenById(id);

	        byte[] pvData = exam.getPV();


	        ByteArrayResource resource = new ByteArrayResource(pvData);

	        String uniqueFilename = "PV_" + exam.getTitreExamen() + ".pdf";

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_PDF);
	        headers.setContentDispositionFormData("attachment", uniqueFilename);

	        return ResponseEntity.ok()
	                .headers(headers)
	                .body(resource);
	    }

    @GetMapping("/downloadEpreuve/{id}")

    public ResponseEntity<ByteArrayResource> downloadPrevuve(@PathVariable Long id) {
        // Retrieve the byte array of the prevuve file from the database
    	 Examen exam = examenService.getExamenById(id);

	  
        byte[] prevuveData = exam.getEpreuve();

        // Create a ByteArrayResource to wrap the byte array
        ByteArrayResource resource = new ByteArrayResource(prevuveData);

       
       
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF); // Set content type as PDF or the appropriate MIME type
        headers.setContentDispositionFormData("attachment", "filename.pdf"); // Set filename for download

        // Return the response entity with file content and headers
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
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
	public String  SubmitExam (@RequestParam List<String>teacherCount,@RequestParam(name = "salles", required = false) List<String>salles,@RequestParam String NomExamen,@RequestParam String elementPed,@RequestParam String session,@RequestParam String method,@RequestParam String  DateExam,@RequestParam String  HeurExam,@RequestParam String  DureeExam,@RequestParam String rapport,@RequestParam String semestre,@RequestParam String TypeExamen,String AnneeUniversitaire ,@RequestParam MultipartFile prevuve,@RequestParam MultipartFile pv,RedirectAttributes redirectAttributes) {
		if(salles==null) {redirectAttributes.addFlashAttribute("error", "Veuillez choisir une salle.");
    	
   	 return "redirect:/AddExamen";}
		List<Enseignant> ListeEnseignants =new ArrayList<>();	

		Examen  exam =new Examen();
		Long elementId = Long.valueOf(Integer.parseInt(elementPed)); 
		
		
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
		    Niveau niveau =elementServiceImpl.getElementById(elementId).getNiveau();
			System.out.print(ExamIntervalStart+","+ExamIntervalEnd);
		List<String>ListHeurs=Human_ResourcesServiceImpl.getListHeurExamen(DateExamen, ExamIntervalStart, ExamIntervalEnd,niveau);
		if(ListHeurs.size()!=0) {
			StringBuilder errorMessage = new StringBuilder("This class already has an exam scheduled at");

			for (String heur : ListHeurs) {
			    errorMessage.append(heur).append(", ");
			}

			errorMessage.setLength(errorMessage.length() - 2);
			 redirectAttributes.addFlashAttribute("error",errorMessage);
			
		        return "redirect:/AddExamen";	
		}
		List<Administrateur> ListeAdministrateur=Human_ResourcesServiceImpl.getAvailableAdministrateur(DateExamen, ExamIntervalStart, ExamIntervalEnd);

		Set<Salle> ListeUnavailableSalles=Human_ResourcesServiceImpl.getUnavailableSalles(DateExamen, ExamIntervalStart, ExamIntervalEnd);
		Enseignant coordinateur1 =null;
		List<Surveillance> listeSurveillance=new ArrayList<>();
        Random rand = new Random();

		if("parGroupe".equals(method)) {
			 int minNumberSurveillance = 100000;

			 for (int  i = 0 ; i<salles.size();i++) {
					
		         String[] parts = salles.get(i).split("-");
		         int IdSalle = Integer.parseInt(parts[0]);
                 int computedIndex = Integer.parseInt(parts[1]);
                 int NumberSurveillance= Integer.parseInt(teacherCount.get(computedIndex));
                 if (NumberSurveillance < minNumberSurveillance) {
                     minNumberSurveillance = NumberSurveillance;
                     
                 }
                 }
			System.out.print("--------mmm------------------"+salles.size()+"-----------------mmmm---------");
			List<Groupe> ListGroupes=Human_ResourcesServiceImpl.getAvailableTeachersByGroupe(DateExamen, ExamIntervalStart, ExamIntervalEnd,minNumberSurveillance );
			
			if(ListGroupes.size()<1) {
				redirectAttributes.addFlashAttribute("error","groupe not disponible");
				
		        return "redirect:/AddExamen";
			}
			
			
			Random random = new Random();
	        int randomGroupIndex = random.nextInt(ListGroupes.size());
	        Groupe selectedGroup = ListGroupes.get(randomGroupIndex);

	        int randomTeacherIndex = random.nextInt(selectedGroup.getListeEnseignants().size());
	        coordinateur1 = selectedGroup.getListeEnseignants().get(randomTeacherIndex);
	        exam.setEnseignantCoordonne(coordinateur1);
	        selectedGroup.getListeEnseignants().remove(randomTeacherIndex);
			GroupeService.sortGroupsByTeacherCount(ListGroupes);

			for (int  i = 0 ; i<salles.size();i++) {
				 String[] parts = salles.get(i).split("-");
		         int IdSalle = Integer.parseInt(parts[0]);
                 int computedIndex = Integer.parseInt(parts[1]);
                 int NumberSurveillance= Integer.parseInt(teacherCount.get(computedIndex));
                 int p=0;
                 while(NumberSurveillance > ListGroupes.get(p).getListeEnseignants().size()  ) {
                	 p++;
                	 if(p==ListGroupes.size()) {
                		 redirectAttributes.addFlashAttribute("error","groupe not disponible");
         				
         		        return "redirect:/AddExamen"; 
                	 }
                 }
                 Surveillance s =new Surveillance() ;
	             List <Enseignant> listEnseignantSurveillance = new ArrayList<>();
	             
	             for (int j =0;j<NumberSurveillance;j++) {
			        	
                     int index = rand.nextInt(ListGroupes.get(p).getListeEnseignants().size());
    	             listEnseignantSurveillance.add(ListGroupes.get(p).getListeEnseignants().get(index));
    	             ListGroupes.get(p).getListeEnseignants().remove(index);
                     
                                                    }
	             if(ListeAdministrateur.size()<1) {
                     redirectAttributes.addFlashAttribute("error", "admin not disponible");
    	             return "redirect:/AddExamen";
                                          }
                else { 
        	                int indexAD = rand.nextInt(ListeAdministrateur.size());
    	                    s.setCadreAdministrateur(ListeAdministrateur.get(indexAD));
                            ListeAdministrateur.remove(indexAD);
                 }
	             
	             Long idSalleSur = Long.valueOf(IdSalle); 
	               	 			

	 			
	             
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
                 
                 
				
			}
			
		
			
            
		}
		else {
			      ListeEnseignants=Human_ResourcesServiceImpl.getAvailableTeachers(DateExamen, ExamIntervalStart, ExamIntervalEnd);
			      if(ListeEnseignants.size()<1) {
				            redirectAttributes.addFlashAttribute("error", "Le nombre de surveillants est supérieur au nombre d'enseignants.");
			                return "redirect:/AddExamen";
			                                    }
			      int index1 = rand.nextInt(ListeEnseignants.size());
			      coordinateur1=ListeEnseignants.get(index1);
			      ListeEnseignants.remove(index1);
			      exam.setEnseignantCoordonne(coordinateur1); 
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
		               if(ListeAdministrateur.size()<1) {
		                         redirectAttributes.addFlashAttribute("error", "admin not disponible");
		        	             return "redirect:/AddExamen";
		                                              }
		               else { 
		            	   int indexAD = rand.nextInt(ListeAdministrateur.size());
		        	       s.setCadreAdministrateur(ListeAdministrateur.get(indexAD));
		                   ListeAdministrateur.remove(indexAD);
		                     }
				       Long idSalleSur = Long.valueOf(IdSalle); 
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
			      	
		}
		


		  

		exam.setTitreExamen(NomExamen);
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
		exam.setElementP(elementServiceImpl.getElementById(elementId));
		exam.setDurationMinutes(Integer.parseInt(DureeExam));
		exam.setRealDurationMinutes(Integer.parseInt(DureeExam));
		Long elementsemestre = Long.valueOf(Integer.parseInt(semestre));
		
		Long TypeExamenId = Long.valueOf(Integer.parseInt(TypeExamen));
		exam.setTypeExamen(TypeExamenImpl.getTypeExamenById(TypeExamenId));

		exam.setSemestre(semestreService.getSemestreById(elementsemestre));
		exam.setListeSurveillance(listeSurveillance);
		exam.setAnneeUniversitaire(AnneeUniversitaire);
		try {
			exam.setEpreuve(prevuve.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			exam.setPV(pv.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		exam.setRapport(rapport);
		if(Human_ResourcesServiceImpl.ExamenIsExist(elementServiceImpl.getElementById(elementId),TypeExamenImpl.getTypeExamenById(TypeExamenId),AnneeUniversitaire,sessionServiceimpl.getSessionById(elementSession))) {
			 redirectAttributes.addFlashAttribute("error","deja exam exist");
			 return "redirect:/AddExamen";
		}
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
