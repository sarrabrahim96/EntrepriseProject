package com.example.saif.saifproject.Service;

import com.example.saif.saifproject.Entity.Entreprise;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface EntrepriseService {
    // Créer ou mettre à jour une entreprise
    Entreprise saveEntreprise(Entreprise entreprise);

    // Récupérer une entreprise par ID
    Optional<Entreprise> getEntrepriseById(Long id);

    // Récupérer toutes les entreprises avec pagination et tri
    Page<Entreprise> getAllEntreprises(int page, int size, String sortBy);

    // Supprimer une entreprise
    void deleteEntreprise(Long id);

    // Récupérer une entreprise par son nom
    List<Entreprise> getEntreprisesByNom(String nom);
}
