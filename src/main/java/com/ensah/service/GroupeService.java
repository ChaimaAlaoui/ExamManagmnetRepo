package com.ensah.service;

import java.util.List;

import com.ensah.bo.Groupe;

public interface GroupeService {
	
     List<Groupe> getAllGroupes();
	
     Groupe saveGroupe(Groupe groupe);
	
     Groupe getGroupeById(Long id);
 	
     Groupe updateGroupe(Groupe groupe);
	
	 void deleteGroupeById(Long id);

}
