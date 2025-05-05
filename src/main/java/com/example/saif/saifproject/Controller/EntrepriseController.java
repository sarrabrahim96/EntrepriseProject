package com.example.saif.saifproject.Controller;

import com.example.saif.saifproject.Entity.Entreprise;
import com.example.saif.saifproject.Service.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/entreprises")
public class EntrepriseController {

    @Autowired
    private EntrepriseService entrepriseService;

    @GetMapping
    public String listEntreprises(Model model) {
        List<Entreprise> entreprises = entrepriseService.getAllEntreprises(0, 10, "nom").getContent();
        model.addAttribute("entreprises", entreprises);
        model.addAttribute("newEntreprise", new Entreprise());
        return "entreprise/list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("entreprise", new Entreprise());
        return "entreprise/form";
    }

    @PostMapping
    public String save(@ModelAttribute Entreprise entreprise) {
        entrepriseService.saveEntreprise(entreprise);
        return "redirect:/entreprises";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Entreprise entreprise = entrepriseService.getEntrepriseById(id).orElseThrow();
        model.addAttribute("entreprise", entreprise);
        return "entreprise/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        entrepriseService.deleteEntreprise(id);
        return "redirect:/entreprises";
    }
}