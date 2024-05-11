package com.ensah.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensah.bo.Enseignant;

public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {

}
