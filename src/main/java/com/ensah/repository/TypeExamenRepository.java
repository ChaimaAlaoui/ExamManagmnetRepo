package com.ensah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensah.bo.TypeExamen;


@Repository
public interface TypeExamenRepository extends JpaRepository<TypeExamen, Long> {

}