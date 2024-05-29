package com.ensah.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.ensah.bo.Administrateur;
import com.ensah.service.AdministrateurService;
import com.ensah.repository.AdministrateurRepository;

@Service
public class AdministrateurServiceImpl implements AdministrateurService{

		private AdministrateurRepository administrateurRepository;
		
		public AdministrateurServiceImpl(AdministrateurRepository administrateurRepository) {
			super();
			this.administrateurRepository = administrateurRepository;
		}

		@Override
		public List<Administrateur> getAllAdministrateurs() {
			return administrateurRepository.findAll();
		}

		@Override
		public Administrateur saveAdministrateur(Administrateur administrateur) {
			return administrateurRepository.save(administrateur);
		}

		@Override
		public Administrateur getAdministrateurById(Long id) {
			return administrateurRepository.findById(id).get();
		}

		@Override
		public Administrateur updateAdministrateur(Administrateur administrateur) {
			return administrateurRepository.save(administrateur);
		}

		@Override
		public void deleteAdministrateurById(Long id) {
			administrateurRepository.deleteById(id);	
		}

	


}

