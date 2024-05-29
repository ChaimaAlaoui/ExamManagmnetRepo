package com.ensah.service.impl;

import java.util.Date;
import java.util.List;
import java.util.HashSet;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensah.bo.Enseignant;
import com.ensah.bo.Examen;
import com.ensah.bo.Salle;
import com.ensah.bo.Surveillance;
import com.ensah.Algorithme.*;
import com.ensah.service.EnseignantService;
import com.ensah.service.ExamenService;
import com.ensah.service.Human_ResourcesService;
import com.ensah.service.SalleService;
@Service
public class Human_ResourcesServiceImpl implements Human_ResourcesService {
@Autowired 
private ExamenService ExamenServiceImpl;
@Autowired 
private EnseignantService EnseignantServiceImpl;
@Autowired 
private  SalleService SalleServiceImpl;
	@Override
	public List<Enseignant> getAvailableTeachers(Date Date, float StartedHour, float EndedHour) {
		List<Examen> ListeExamens = ExamenServiceImpl.getAllExamenByDate(Date);
		List<Enseignant> listAllTeachers=EnseignantServiceImpl.getAllEnseignants();
		Set<Enseignant> listEliminatedTeachers = new HashSet<>();
		Interval myInterval=new Interval(StartedHour,StartedHour);
		for (Examen i : ListeExamens) {
			
			float ExamIntervalStart =TimeConverter.convertTimeToFloat(i.getHeur());
			
			

LocalTime time = LocalTime.parse(i.getHeur());

		int Duration = i.getDurationMinutes() ;
		 LocalTime updatedTime = time.plusMinutes(Duration);
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	        String formattedTime = updatedTime.format(formatter);
		    float ExamIntervalEnd =TimeConverter.convertTimeToFloat(formattedTime);

			Interval ExamInterval=new Interval(ExamIntervalStart,ExamIntervalEnd);
			if(Interval.getIntersection(ExamInterval, myInterval)) {
				for(Surveillance k : i.getListeSurveillance()) {
					listEliminatedTeachers.add(k.getEnseignantCoordonneSurveillance());
					for(Enseignant e:k.getEnseignantSurveillanceList()) {
						listEliminatedTeachers.add(e);
						
					}
				}
			}

		}
		listAllTeachers.removeAll(listEliminatedTeachers);
		return listAllTeachers;
	}
	@Override
	public Set<Salle> getUnavailableSalles(Date Date, float StartedHour, float EndedHour) {
		List<Examen> ListeExamens = ExamenServiceImpl.getAllExamenByDate(Date);
		
		Set<Salle> listEliminatedSalles = new HashSet<>();
		Interval myInterval=new Interval(StartedHour,StartedHour);
		for (Examen i : ListeExamens) {
			float ExamIntervalStart =TimeConverter.convertTimeToFloat(i.getHeur());
			LocalTime time = LocalTime.parse(i.getHeur());

			int Duration = i.getDurationMinutes() ;
			 LocalTime updatedTime = time.plusMinutes(Duration);
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		        String formattedTime = updatedTime.format(formatter);
			    float ExamIntervalEnd =TimeConverter.convertTimeToFloat(formattedTime);

			Interval ExamInterval=new Interval(ExamIntervalStart,ExamIntervalEnd);
			if(Interval.getIntersection(ExamInterval, myInterval)) {
				for(Surveillance k : i.getListeSurveillance()) {
					listEliminatedSalles.add(k.getSalle());				
				}
			}

		}
		
		return listEliminatedSalles;
	}

}