package com.example.saif.saifproject.Controller;


import com.example.saif.saifproject.Entity.Projet;
import com.example.saif.saifproject.Service.EntrepriseService;
import com.example.saif.saifproject.Service.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projets")
public class ProjetController {

    @Autowired
    private ProjetService projetService;

    @Autowired
    private EntrepriseService entrepriseService;

    @GetMapping
    public String listProjets(Model model) {
        List<Projet> projets = projetService.getAllProjets(0, 10, "nom").getContent();
        model.addAttribute("projets", projets);
        return "projet/list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("projet", new Projet());
        model.addAttribute("entreprises", entrepriseService.getAllEntreprises(0, 100, "nom").getContent());
        return "projet/form";
    }

    @PostMapping
    public String save(@ModelAttribute Projet projet) {
        projetService.saveProjet(projet);
        return "redirect:/projets";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Projet projet = projetService.getProjetById(id).orElseThrow();
        model.addAttribute("projet", projet);
        model.addAttribute("entreprises", entrepriseService.getAllEntreprises(0, 100, "nom").getContent());
        return "projet/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        projetService.deleteProjet(id);
        return "redirect:/projets";
    }
}
