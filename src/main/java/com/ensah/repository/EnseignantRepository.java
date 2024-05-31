package com.ensah.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensah.bo.Enseignant;

public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {

}
