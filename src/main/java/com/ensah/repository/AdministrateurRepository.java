package com.ensah.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensah.bo.Administrateur;

public interface AdministrateurRepository extends JpaRepository<Administrateur, Long> {

}
