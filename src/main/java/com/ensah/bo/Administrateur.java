package com.ensah.bo;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name="idCardreAdmin")
public class Administrateur extends Personnel {

   private String grade;

public String getGrade() {
	return grade;
}

public void setGrade(String grade) {
	this.grade = grade;
}
@OneToMany(mappedBy = "administrateur", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
List<Surveillance> ListeSurveillance=new ArrayList<>();

public List<Surveillance> getListeSurveillance() {
	return ListeSurveillance;
}

public void setListeSurveillance(List<Surveillance> listeSurveillance) {
	ListeSurveillance = listeSurveillance;
}



   
   
}