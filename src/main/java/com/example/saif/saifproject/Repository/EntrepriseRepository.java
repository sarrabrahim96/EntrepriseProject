package com.example.saif.saifproject.Repository;

import com.example.saif.saifproject.Entity.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
    List<Entreprise> findByNom(String nom);

}
