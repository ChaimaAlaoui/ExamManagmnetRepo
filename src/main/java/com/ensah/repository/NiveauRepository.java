package com.ensah.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensah.bo.ElementPedagogique;
import com.ensah.bo.Niveau;
import com.ensah.bo.TypeElement;
@Repository
public interface NiveauRepository extends JpaRepository<Niveau, Long> {

}
