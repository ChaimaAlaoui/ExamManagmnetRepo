package com.ensah.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.ensah.bo.Administrateur;
import com.ensah.bo.ElementPedagogique;
import com.ensah.bo.Enseignant;
import com.ensah.bo.Groupe;
import com.ensah.bo.Salle;
import com.ensah.bo.Session;
import com.ensah.bo.TypeExamen;
import com.ensah.bo.Niveau;

public interface Human_ResourcesService {
public List<Enseignant> getAvailableTeachers(Date Date ,float  StartedHour ,float EndedHour );
public List<Groupe> getAvailableTeachersByGroupe(Date Date ,float  StartedHour ,float EndedHour,int total );

public List<Administrateur> getAvailableAdministrateur(Date Date ,float  StartedHour ,float EndedHour );

public Set<Salle> getUnavailableSalles(Date Date ,float  StartedHour ,float EndedHour );
public List<String> getListHeurExamen (Date Date ,float  StartedHour ,float EndedHour,Niveau niveau );
public boolean ExamenIsExist (ElementPedagogique element ,TypeExamen  typeExamen ,String AnneeUniversitaire,Session session );

}