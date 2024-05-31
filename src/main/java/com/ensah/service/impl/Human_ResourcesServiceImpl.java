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

import com.ensah.bo.Groupe;

import com.ensah.bo.Administrateur;
import com.ensah.bo.ElementPedagogique;
import com.ensah.bo.Enseignant;
import com.ensah.bo.Examen;
import com.ensah.bo.Niveau;
import com.ensah.bo.Salle;
import com.ensah.bo.Session;
import com.ensah.bo.Surveillance;
import com.ensah.bo.TypeExamen;
import com.ensah.Algorithme.*;
import com.ensah.service.AdministrateurService;
import com.ensah.service.EnseignantService;
import com.ensah.service.ExamenService;
import com.ensah.service.GroupeService;
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
@Autowired 
private AdministrateurService AdministrateurServiceImpl;
@Autowired 
private GroupeService GroupeServiceImpl;
	@Override
	public List<Enseignant> getAvailableTeachers(Date Date, float StartedHour, float EndedHour) {
		List<Examen> ListeExamens = ExamenServiceImpl.getAllExamenByDate(Date);
		List<Enseignant> listAllTeachers=EnseignantServiceImpl.getAllEnseignants();
		Set<Enseignant> listEliminatedTeachers = new HashSet<>();
		Interval myInterval=new Interval(StartedHour,EndedHour);
		for (Examen i : ListeExamens) {
			
			float ExamIntervalStart =TimeConverter.convertTimeToFloat(i.getHeur());
			
			listEliminatedTeachers.add(i.getEnseignantCoordonne());


LocalTime time = LocalTime.parse(i.getHeur());

		int Duration = i.getDurationMinutes() ;
		 LocalTime updatedTime = time.plusMinutes(Duration);
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	        String formattedTime = updatedTime.format(formatter);
		    float ExamIntervalEnd =TimeConverter.convertTimeToFloat(formattedTime);

			Interval ExamInterval=new Interval(ExamIntervalStart,ExamIntervalEnd);
			if(Interval.getIntersection(ExamInterval, myInterval)) {
				for(Surveillance k : i.getListeSurveillance()) {
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
	@Override
	public List<String> getListHeurExamen (Date Date ,float  StartedHour ,float EndedHour,Niveau niveau ){
List<Examen> ListeExamens = ExamenServiceImpl.getAllExamenByDate(Date);
		List listHeurs=new ArrayList<>();
		Interval myInterval=new Interval(StartedHour,EndedHour);
		for (Examen i : ListeExamens) {
			if(i.getElementP().getNiveau().equals(niveau)) {
				float ExamIntervalStart =TimeConverter.convertTimeToFloat(i.getHeur());
				LocalTime time = LocalTime.parse(i.getHeur());

				int Duration = i.getDurationMinutes() ;
				 LocalTime updatedTime = time.plusMinutes(Duration);
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			        String formattedTime = updatedTime.format(formatter);
				    float ExamIntervalEnd =TimeConverter.convertTimeToFloat(formattedTime);

				Interval ExamInterval=new Interval(ExamIntervalStart,ExamIntervalEnd);
				if(Interval.getIntersection(ExamInterval, myInterval)) {
					System.out.print("myexam :"+ExamIntervalStart+","+ExamIntervalEnd);

					listHeurs.add("["+i.getHeur()+","+formattedTime+"]");
				}
		}}
		return listHeurs;
	}
	@Override
	public boolean ExamenIsExist(ElementPedagogique element, TypeExamen typeExamen, String AnneeUniversitaire,
		 Session session) {
		List<Examen> ListeExamens=	ExamenServiceImpl.getAllExamenByAnnee(AnneeUniversitaire);
		for (Examen i : ListeExamens) {
			
			if(i.getSession().equals(session)&&i.getTypeExamen().equals(typeExamen)&&i.getElementP().equals(element)) {
				
				return true;
				
			}
			
		}
		return false;
	}
	@Override
	public List<Administrateur> getAvailableAdministrateur(Date Date, float StartedHour, float EndedHour) {
		List<Examen> ListeExamens = ExamenServiceImpl.getAllExamenByDate(Date);
		List<Administrateur> listAllAdministrateurs=AdministrateurServiceImpl.getAllAdministrateurs();
		Set<Administrateur> listEliminatedAdministrateurs = new HashSet<>();
		Interval myInterval=new Interval(StartedHour,EndedHour);
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
					listEliminatedAdministrateurs.add(k.getCadreAdministrateur());
					
				}
			}

		}
		listAllAdministrateurs.removeAll(listEliminatedAdministrateurs);
		return listAllAdministrateurs;
	}
	@Override
	public List<Groupe> getAvailableTeachersByGroupe(Date Date, float StartedHour, float EndedHour, int total) {
		List<Examen> ListeExamens = ExamenServiceImpl.getAllExamenByDate(Date);
		
		List<Groupe> listAllGroupes=GroupeServiceImpl.getAllGroupes();
System.out.print(listAllGroupes.size());
		Set<Enseignant> listEliminatedTeachers = new HashSet<>();
		Interval myInterval=new Interval(StartedHour,EndedHour);
		for (Examen i : ListeExamens) {
			
			float ExamIntervalStart =TimeConverter.convertTimeToFloat(i.getHeur());
			
			listEliminatedTeachers.add(i.getEnseignantCoordonne());

LocalTime time = LocalTime.parse(i.getHeur());

		int Duration = i.getDurationMinutes() ;
		 LocalTime updatedTime = time.plusMinutes(Duration);
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	        String formattedTime = updatedTime.format(formatter);
		    float ExamIntervalEnd =TimeConverter.convertTimeToFloat(formattedTime);

			Interval ExamInterval=new Interval(ExamIntervalStart,ExamIntervalEnd);
			if(Interval.getIntersection(ExamInterval, myInterval)) {
				for(Surveillance k : i.getListeSurveillance()) {
					for(Enseignant e:k.getEnseignantSurveillanceList()) {
						listEliminatedTeachers.add(e);
						
					}
				}
			}

		}
		Set<Groupe> listEliminatedGroupes = new HashSet<>();

		for (Groupe i :listAllGroupes ) {
			i.getListeEnseignants().removeAll(listEliminatedTeachers);
			if(i.getListeEnseignants().size()<total) {
				listEliminatedGroupes.add(i);
			}
		}
		listAllGroupes.removeAll(listEliminatedGroupes);
		return listAllGroupes;
	}

	}