package com.example.saif.saifproject.Controller;


import com.example.saif.saifproject.Service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/upload")
    public String showUploadForm() {
        return "file/upload-form";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        try {
            String fileName = fileStorageService.saveFile(file);
            model.addAttribute("message", "Fichier téléchargé : " + fileName);
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors du téléchargement : " + e.getMessage());
        }
        return "file/upload-result";
    }
}

