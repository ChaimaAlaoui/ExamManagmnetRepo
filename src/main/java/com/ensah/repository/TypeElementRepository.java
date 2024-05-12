package com.ensah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensah.bo.Administrateur;
import com.ensah.bo.TypeElement;
@Repository
public interface  TypeElementRepository extends JpaRepository<TypeElement, Long> {

}
