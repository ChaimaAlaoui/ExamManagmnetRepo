package com.ensah.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensah.bo.Examen;
@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long> {
    List<Examen> findAllByDate(Date date);
    List<Examen> findAllByAnneeUniversitaire(String anneeUniversitaire);

}