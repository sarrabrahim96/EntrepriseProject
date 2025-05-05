package com.example.saif.saifproject.Repository;



import com.example.saif.saifproject.Entity.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TacheRepository extends JpaRepository<Tache, Long> {
    List<Tache> findByEmployesId(Long employeId);

    List<Tache> findByProjetId(Long projetId);

    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}
