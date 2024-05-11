package com.ensah.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.ensah.bo.Enseignant;
import com.ensah.service.EnseignantService;


import com.ensah.repository.EnseignantRepository;

@Service
public class EnseignantServiceImpl implements EnseignantService{

		private EnseignantRepository enseignantRepository;
		
		public EnseignantServiceImpl(EnseignantRepository enseignantRepository) {
			super();
			this.enseignantRepository = enseignantRepository;
		}

		@Override
		public List<Enseignant> getAllEnseignants() {
			return enseignantRepository.findAll();
		}

		@Override
		public Enseignant saveEnseignant(Enseignant enseignant) {
			return enseignantRepository.save(enseignant);
		}

		@Override
		public Enseignant getEnseignantById(Long id) {
			return enseignantRepository.findById(id).get();
		}

		@Override
		public Enseignant updateEnseignant(Enseignant enseignant) {
			return enseignantRepository.save(enseignant);
		}

		@Override
		public void deleteEnseignantById(Long id) {
			enseignantRepository.deleteById(id);	
		}

	


}
