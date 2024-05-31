package com.ensah.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.ensah.bo.Groupe;

public interface GroupeService {
	
     List<Groupe> getAllGroupes();
	
     Groupe saveGroupe(Groupe groupe);
	
     Groupe getGroupeById(Long id);
 	
     Groupe updateGroupe(Groupe groupe);
	
	 void deleteGroupeById(Long id);
	 public static void sortGroupsByTeacherCount(List<Groupe> ListGroupes) {
	        Collections.sort(ListGroupes, new Comparator<Groupe>() {
	            @Override
	            public int compare(Groupe g1, Groupe g2) {
	                return Integer.compare(g1.getListeEnseignants().size(), g2.getListeEnseignants().size());
	            }
	        });
	    }
}
