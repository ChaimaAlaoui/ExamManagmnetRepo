package com.ensah.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.ensah.bo.Enseignant;
import com.ensah.bo.Salle;

public interface Human_ResourcesService {
public List<Enseignant> getAvailableTeachers(Date Date ,float  StartedHour ,float EndedHour );
public Set<Salle> getUnavailableSalles(Date Date ,float  StartedHour ,float EndedHour );

}