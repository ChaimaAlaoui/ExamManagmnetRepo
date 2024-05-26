package com.ensah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensah.bo.Examen;
@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long> {

}