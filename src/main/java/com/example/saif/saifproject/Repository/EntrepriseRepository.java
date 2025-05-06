package com.example.saif.saifproject.Repository;

import com.example.saif.saifproject.Entity.Entreprise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
    Page<Entreprise> findByNomContainingIgnoreCase(String nom , Pageable pageable);

}
