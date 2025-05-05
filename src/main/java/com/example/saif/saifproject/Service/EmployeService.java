package com.example.saif.saifproject.Service;

import com.example.saif.saifproject.Entity.Employe;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface EmployeService {
    Employe saveEmploye(Employe employe);

    // Récupérer un employé par ID
    Optional<Employe> getEmployeById(Long id);

    // Récupérer tous les employés avec pagination et tri
    Page<Employe> getAllEmployes(int page, int size, String sortBy);

    // Supprimer un employé
    void deleteEmploye(Long id);

    // Récupérer les employés par leur nom
    List<Employe> getEmployesByNom(String nom);
}
