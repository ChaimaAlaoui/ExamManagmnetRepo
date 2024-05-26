package com.ensah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensah.bo.Salle;
import com.ensah.bo.Semestre;
@Repository
public interface SemestreRepository extends JpaRepository<Semestre, Long> {

}