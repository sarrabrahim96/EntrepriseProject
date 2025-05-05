package com.example.saif.saifproject.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface FileStorageService {
    String saveFile(MultipartFile file) throws IOException;

    // Charger un fichier
    Path load(String filename);

    // Supprimer un fichier
    void deleteFile(String filename) throws IOException;
}
