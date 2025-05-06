package com.example.saif.saifproject.Controller;

import com.example.saif.saifproject.Entity.Entreprise;
import com.example.saif.saifproject.Entity.Projet;
import com.example.saif.saifproject.Service.EntrepriseService;
import com.example.saif.saifproject.Service.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Controller
@RequestMapping("/entreprises")
public class EntrepriseController {

    @Autowired
    private EntrepriseService entrepriseService;


    @Autowired
    private ProjetService projetService;




    @GetMapping
    public String listEntreprises(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size,
                                  @RequestParam(required = false) String search,
                                  Model model) {
        Page<Entreprise> entreprisePage;

        if (search != null && !search.trim().isEmpty()) {
            entreprisePage = entrepriseService.searchEntreprisesByNom(search, page, size);
        } else {
            entreprisePage = entrepriseService.getAllEntreprises(page, size, "nom");
        }

        model.addAttribute("entreprisePage", entreprisePage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", entreprisePage.getTotalPages());
        model.addAttribute("search", search); // Pour pré-remplir le champ dans la vue

        return "entreprise/list";
    }


    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("entreprise", new Entreprise());
        return "entreprise/form";
    }

    @PostMapping()
    public String saveEntreprise(@ModelAttribute Entreprise entreprise,
                                 @RequestParam("logoFile") MultipartFile logoFile) {
        try {
            entrepriseService.saveEntrepriseWithLogo(entreprise, logoFile);

            return entreprise.getId() != null ?
                    "redirect:/entreprises?updated=true" :
                    "redirect:/entreprises?added=true";
        } catch (Exception e) {
            return entreprise.getId() != null ?
                    "redirect:/entreprises/edit/" + entreprise.getId() + "?error=true" :
                    "redirect:/entreprises/new?error=true";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Entreprise entreprise = entrepriseService.getEntrepriseById(id).orElseThrow();
        model.addAttribute("entreprise", entreprise);
        return "entreprise/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteEntreprise(@PathVariable Long id) {
        entrepriseService.deleteEntreprise(id);
        return "redirect:/entreprises?deleted=true";
    }


}