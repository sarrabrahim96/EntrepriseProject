package com.example.saif.saifproject.Service.Impl;

import com.example.saif.saifproject.Entity.Entreprise;
import com.example.saif.saifproject.Repository.EntrepriseRepository;
import com.example.saif.saifproject.Service.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EntrepriseServiceImpl implements EntrepriseService {
    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Override
    public Entreprise saveEntreprise(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }

    @Override
    public Optional<Entreprise> getEntrepriseById(Long id) {
        return entrepriseRepository.findById(id);
    }

    @Override
    public Page<Entreprise> getAllEntreprises(int page, int size, String sortBy) {
        return entrepriseRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
    }

    @Override
    public void deleteEntreprise(Long id) {
        entrepriseRepository.deleteById(id);
    }

    @Override
    public List<Entreprise> getEntreprisesByNom(String nom) {
        return entrepriseRepository.findByNom(nom);
    }
}
