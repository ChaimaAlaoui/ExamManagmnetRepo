package com.ensah.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ensah.bo.Departement;
import com.ensah.service.DepartementService;
@Controller
public class DepartmentController {
	  @Autowired
	    private DepartementService dptService;

	    @GetMapping("/departments")
	    public String listGroupes(Model model) {
	        List<Departement> departments = dptService. getAllDepartements();

	        model.addAttribute("departments", departments);
	        return "department-list"; // Make sure you have a Thymeleaf template named 'groupe-list.html'
	    }

	    @GetMapping("/departments/{id}")
	    public String getDepartmentById(@PathVariable Long id, Model model) {
	    	Departement departement = dptService.getDepartementById(id);
	        model.addAttribute("departement", departement);
	        return "departement-detail"; // Make sure you have a Thymeleaf template named 'groupe-detail.html'
	    }

	    @GetMapping("/departments/add")
	    public String addDepartmentForm(Model model) {
	    	
	        model.addAttribute("departement", new Departement());
	        return "add_department"; // Make sure you have a Thymeleaf template named 'groupe-add.html'
	    }

	    @PostMapping("/departments/add")
	    public String saveDepartment(@ModelAttribute Departement department) {
	        dptService.saveDepartment(department);
	        return "redirect:/departments";
	    }

	    @GetMapping("/departments/edit/{id}")
	    public String editDepartmentForm(@PathVariable Long id, Model model) {
	      Departement departement = dptService.getDepartementById(id);
	        model.addAttribute("department", departement);
	        return "edit_department"; // Make sure you have a Thymeleaf template named 'groupe-edit.html'
	    }

	    @PostMapping("/departments/edit/{id}")
	    public String updateDepartement(@PathVariable Long id, @ModelAttribute Departement department) {
	    	department.setIdDepartement(id);
	    	dptService.saveDepartment(department);
	        return "redirect:/departments";
	    }

	    @GetMapping("/departments/delete/{id}")
	    public String deleteGroupe(@PathVariable Long id) {
	    	dptService.deleteDepartementById(id);
	        return "redirect:/departments";
	    }
	


}
