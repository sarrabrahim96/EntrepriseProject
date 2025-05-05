package com.example.saif.saifproject.Controller;

import com.example.saif.saifproject.Entity.Employe;
import com.example.saif.saifproject.Service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employes")
public class EmployeController {

    @Autowired
    private EmployeService employeService;

    @GetMapping
    public String listEmployes(Model model) {
        model.addAttribute("employes", employeService.getAllEmployes(0, 20, "nom").getContent());
        return "employe/list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("employe", new Employe());
        return "employe/form";
    }

    @PostMapping
    public String save(@ModelAttribute Employe employe) {
        employeService.saveEmploye(employe);
        return "redirect:/employes";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Employe employe = employeService.getEmployeById(id).orElseThrow();
        model.addAttribute("employe", employe);
        return "employe/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        employeService.deleteEmploye(id);
        return "redirect:/employes";
    }
}
