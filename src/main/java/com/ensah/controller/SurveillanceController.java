package com.ensah.controller;

import com.ensah.bo.Enseignant;
import com.ensah.bo.Surveillance;
import com.ensah.service.ExamenService;
import com.ensah.service.SurveillanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SurveillanceController {

    @Autowired
    private SurveillanceService surveillanceService;

    @Autowired
    private ExamenService examenService;

    @GetMapping("/exam/surveillance/{id}")
    public String getSurveillanceDetails(@PathVariable Long id, Model model) {
        List<Surveillance> surveillanceList = examenService.getExamenById(id).getListeSurveillance();
        model.addAttribute("surveillanceList", surveillanceList);
        
        return "surveillance";
    }

    @PostMapping("/viewSurveillants")
    public String viewSurveillants(Long id, Model model) {
        Surveillance surveillance = surveillanceService.getSurveillanceById(id);
        List<Enseignant> surveillants = surveillance.getEnseignantSurveillanceList();
        model.addAttribute("surveillants", surveillants);
        model.addAttribute("surveillanceID", id);

        return "Surveillants";
    }
    
    @PostMapping("/exam/delete/{id}")
    public String deleteExam(@PathVariable Long id, Model model) {
        // Call the service method to delete the exam by ID
        examenService.deleteExamenById(id);
        // Redirect to the exam list page
        return "redirect:/ListeExamens";
    }
}

