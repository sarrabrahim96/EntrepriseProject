package com.example.saif.saifproject.Service;

import com.example.saif.saifproject.Entity.Projet;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProjetService {
    // Créer ou mettre à jour un projet
    Projet saveProjet(Projet projet);

    // Récupérer un projet par ID
    Optional<Projet> getProjetById(Long id);

    // Récupérer tous les projets avec pagination et tri
    Page<Projet> getAllProjets(int page, int size, String sortBy);

    // Récupérer les projets par entreprise
    List<Projet> getProjetsByEntrepriseId(Long entrepriseId);

    // Supprimer un projet
    void deleteProjet(Long id);
}
