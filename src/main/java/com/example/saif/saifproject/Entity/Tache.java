package com.example.saif.saifproject.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le titre de la tâche est obligatoire")
    private String titre;

    @NotBlank(message = "Le statut de la tâche est obligatoire")
    private String statut; // A_FAIRE, EN_COURS, TERMINE...

    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;

    @ManyToMany
    @JoinTable(
            name = "tache_employe",
            joinColumns = @JoinColumn(name = "tache_id"),
            inverseJoinColumns = @JoinColumn(name = "employe_id")
    )
    private List<Employe> employes;

}
