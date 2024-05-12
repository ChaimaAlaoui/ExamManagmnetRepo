package com.ensah.bo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
public class Session {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long IdSession;
		private String nomSession ; 
		@OneToMany(mappedBy = "session", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
		private List<Examen> ListeExamens =new ArrayList();
		public Long getIdSession() {
			return IdSession;
		}
		public void setIdSession(Long idSession) {
			IdSession = idSession;
		}
		public String getNomSession() {
			return nomSession;
		}
		public void setNomSession(String nomSession) {
			this.nomSession = nomSession;
		}
		public List<Examen> getListeExamens() {
			return ListeExamens;
		}
		public void setListeExamens(List<Examen> listeExamens) {
			ListeExamens = listeExamens;
		}
}
