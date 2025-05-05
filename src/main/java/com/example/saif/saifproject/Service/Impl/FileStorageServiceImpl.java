package com.example.saif.saifproject.Service.Impl;

import com.example.saif.saifproject.Service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public String saveFile(MultipartFile file) throws IOException {
        // Créer un nom unique pour le fichier
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path targetLocation = Paths.get(uploadDir).resolve(fileName);
        Files.copy(file.getInputStream(), targetLocation);
        return fileName; // Retourner le nom du fichier
    }

    @Override
    public Path load(String filename) {
        return Paths.get(uploadDir).resolve(filename);
    }

    @Override
    public void deleteFile(String filename) throws IOException {
        Path filePath = load(filename);
        Files.deleteIfExists(filePath);  // Supprimer le fichier
    }
}
