package com.example.saif.saifproject.Service.Impl;

import com.example.saif.saifproject.Entity.Entreprise;
import com.example.saif.saifproject.Entity.Projet;
import com.example.saif.saifproject.Repository.EntrepriseRepository;
import com.example.saif.saifproject.Repository.ProjetRepository;
import com.example.saif.saifproject.Service.EntrepriseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
@Service
public class EntrepriseServiceImpl implements EntrepriseService {
    @Autowired
    private EntrepriseRepository entrepriseRepository;
    @Autowired
    private ProjetRepository projetRepository;
    private static final Logger logger = LoggerFactory.getLogger(EntrepriseServiceImpl.class);
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public Entreprise saveEntreprise(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }
    @Override
    public void saveEntrepriseWithLogo(Entreprise entreprise, MultipartFile logoFile) {
        if (logoFile != null && !logoFile.isEmpty()) {
            try {
                logger.info("Début du processus de sauvegarde du logo pour l'entreprise : " + entreprise.getNom());

                // Créer le dossier s’il n’existe pas
                File uploadFolder = new File(uploadDir);
                if (!uploadFolder.exists()) {
                    uploadFolder.mkdirs();
                    logger.info("Dossier d'upload créé : " + uploadDir);
                }

                // Générer un nom unique pour éviter les collisions
                String fileName = System.currentTimeMillis() + "_" + logoFile.getOriginalFilename();
                fileName = fileName.replaceAll("\\\\", "/");
                Path filePath = Paths.get(uploadDir, fileName);

                // Sauvegarder le fichier
                Files.write(filePath, logoFile.getBytes());
                logger.info("Fichier logo sauvegardé à : " + filePath.toString());

                // Mettre à jour l'entité avec le chemin du fichier
                entreprise.setLogoPath(fileName);
                logger.info("Chemin du logo mis à jour pour l'entreprise : " + fileName);

            } catch (IOException e) {
                logger.error("Erreur lors de l'enregistrement du fichier logo pour l'entreprise : " + entreprise.getNom(), e);
                throw new RuntimeException("Erreur lors de l'enregistrement du fichier : " + e.getMessage());
            }
        } else {
            logger.warn("Aucun fichier logo téléchargé pour l'entreprise : " + entreprise.getNom());
        }

        // Sauvegarder l'entreprise dans la base de données
        entrepriseRepository.save(entreprise);
        logger.info("Entreprise sauvegardée : " + entreprise.getNom());
    }
    @Override
    public Optional<Entreprise> getEntrepriseById(Long id) {
        return entrepriseRepository.findById(id);
    }

    public Page<Entreprise> getAllEntreprises(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return entrepriseRepository.findAll(pageable);
    }
    @Override
    public void deleteEntreprise(Long id) {
        entrepriseRepository.deleteById(id);
    }

    @Override
    public Page<Entreprise> searchEntreprisesByNom(String nom, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("nom"));
        return entrepriseRepository.findByNomContainingIgnoreCase(nom, pageable);
    }

    public List<Entreprise> getAllEntreprisesList() {
        return entrepriseRepository.findAll();
    }

    public void addProjectToEntreprise(Long entrepriseId, Projet projet) {
        Entreprise entreprise = entrepriseRepository.findById(entrepriseId)
                .orElseThrow(() -> new RuntimeException("Entreprise non trouvée"));
        projet.setEntreprise(entreprise);
        projetRepository.save(projet);
    }


}
