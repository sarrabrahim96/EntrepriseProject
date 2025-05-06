package com.example.saif.saifproject.Service.Impl;

import com.example.saif.saifproject.Entity.Entreprise;
import com.example.saif.saifproject.Entity.Projet;
import com.example.saif.saifproject.Repository.ProjetRepository;
import com.example.saif.saifproject.Service.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProjetServiceImpl implements ProjetService {

    @Autowired
    private ProjetRepository projetRepository;

    @Override
    public Projet saveProjet(Projet projet) {
        return projetRepository.save(projet);
    }

    @Override
    public Optional<Projet> getProjetById(Long id) {
        return projetRepository.findById(id);
    }

    @Override
    public Page<Projet> getAllProjets(int page, int size, String sortBy) {
        return projetRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
    }

    @Override
    public List<Projet> getProjetsByEntrepriseId(Long entrepriseId) {
        return projetRepository.findByEntrepriseId(entrepriseId);
    }
    @Override
    public Page<Projet> searchProjetByTitre(String nom, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("titre"));
        return projetRepository.findByTitreContainingIgnoreCase(nom, pageable);
    }

    @Override
    public void deleteProjet(Long id) {
        projetRepository.deleteById(id);
    }
}
