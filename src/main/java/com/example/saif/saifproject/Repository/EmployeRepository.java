package com.example.saif.saifproject.Repository;

import com.example.saif.saifproject.Entity.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {
    List<Employe> findByNom(String nom);
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}
