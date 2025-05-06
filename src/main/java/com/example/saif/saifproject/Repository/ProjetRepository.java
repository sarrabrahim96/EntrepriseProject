package com.example.saif.saifproject.Repository;

import com.example.saif.saifproject.Entity.Entreprise;
import com.example.saif.saifproject.Entity.Projet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long> {

    List<Projet> findByEntrepriseId(Long entrepriseId);
    Page<Projet> findByTitreContainingIgnoreCase(String nom, Pageable pageable);
}
