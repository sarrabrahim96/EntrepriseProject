package com.example.saif.saifproject.Service;

import com.example.saif.saifproject.Entity.Tache;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface TacheService {
    Tache saveTache(Tache tache);

    // Récupérer une tâche par ID
    Optional<Tache> getTacheById(Long id);

    // Récupérer toutes les tâches avec pagination et tri
    Page<Tache> getAllTaches(int page, int size, String sortBy);

    // Récupérer les tâches par projet
    List<Tache> getTachesByProjetId(Long projetId);

    // Supprimer une tâche
    void deleteTache(Long id);

    // Affecter un employé à une tâche
    void assignEmployeToTache(Long tacheId, Long employeId);
}
