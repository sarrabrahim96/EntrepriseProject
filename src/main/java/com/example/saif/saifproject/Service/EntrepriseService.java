package com.example.saif.saifproject.Service;

import com.example.saif.saifproject.Entity.Entreprise;
import com.example.saif.saifproject.Entity.Projet;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface EntrepriseService {
    // Créer ou mettre à jour une entreprise
    Entreprise saveEntreprise(Entreprise entreprise);
    void saveEntrepriseWithLogo(Entreprise entreprise, MultipartFile logoFile);

    Page<Entreprise> searchEntreprisesByNom(String nom, int page, int size);

    // Récupérer une entreprise par ID
    Optional<Entreprise> getEntrepriseById(Long id);

    // Récupérer toutes les entreprises avec pagination et tri
    Page<Entreprise> getAllEntreprises(int page, int size, String sortBy);

    // Supprimer une entreprise
    void deleteEntreprise(Long id);

    List<Entreprise> getAllEntreprisesList();
    void addProjectToEntreprise(Long entrepriseId, Projet projet);
}
