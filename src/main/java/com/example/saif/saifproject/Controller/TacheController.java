package com.example.saif.saifproject.Controller;

import com.example.saif.saifproject.Entity.Tache;
import com.example.saif.saifproject.Service.EmployeService;
import com.example.saif.saifproject.Service.ProjetService;
import com.example.saif.saifproject.Service.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/taches")
public class TacheController {

    @Autowired
    private TacheService tacheService;

    @Autowired
    private ProjetService projetService;

    @Autowired
    private EmployeService employeService;

    @GetMapping
    public String listTaches(Model model) {
        model.addAttribute("taches", tacheService.getAllTaches(0, 20, "titre").getContent());
        return "taches/list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("tache", new Tache());
        model.addAttribute("projets", projetService.getAllProjets(0, 100, "nom").getContent());
        model.addAttribute("employes", employeService.getAllEmployes(0, 100, "nom").getContent());
        return "taches/form";
    }

    @PostMapping
    public String save(@ModelAttribute Tache tache) {
        tacheService.saveTache(tache);
        return "redirect:/taches";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Tache tache = tacheService.getTacheById(id).orElseThrow();
        model.addAttribute("tache", tache);
        model.addAttribute("projets", projetService.getAllProjets(0, 100, "nom").getContent());
        model.addAttribute("employes", employeService.getAllEmployes(0, 100, "nom").getContent());
        return "taches/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        tacheService.deleteTache(id);
        return "redirect:/taches";
    }
}

