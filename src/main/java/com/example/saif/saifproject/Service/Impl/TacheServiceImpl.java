package com.example.saif.saifproject.Service.Impl;

import com.example.saif.saifproject.Entity.Tache;
import com.example.saif.saifproject.Repository.TacheRepository;
import com.example.saif.saifproject.Service.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TacheServiceImpl implements TacheService {
    @Autowired
    private TacheRepository tacheRepository;

    @Override
    public Tache saveTache(Tache tache) {
        return tacheRepository.save(tache);
    }

    @Override
    public Optional<Tache> getTacheById(Long id) {
        return tacheRepository.findById(id);
    }

    @Override
    public Page<Tache> getAllTaches(int page, int size, String sortBy) {
        return tacheRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
    }

    @Override
    public List<Tache> getTachesByProjetId(Long projetId) {
        return tacheRepository.findByProjetId(projetId);
    }

    @Override
    public void deleteTache(Long id) {
        tacheRepository.deleteById(id);
    }

    @Override
    public void assignEmployeToTache(Long tacheId, Long employeId) {
        Tache tache = tacheRepository.findById(tacheId).orElseThrow(() -> new RuntimeException("Tache non trouvée"));
       // tache.setEmployeId(employeId);  // L'affectation du travailleur à la tâche
        tacheRepository.save(tache);
    }
}
