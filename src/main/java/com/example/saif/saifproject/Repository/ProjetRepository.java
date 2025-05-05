package com.example.saif.saifproject.Repository;

import com.example.saif.saifproject.Entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long> {
    List<Projet> findByEntrepriseNom(String nomEntreprise);

    List<Projet> findByEntrepriseId(Long entrepriseId);


}
