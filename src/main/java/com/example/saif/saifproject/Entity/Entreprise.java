package com.example.saif.saifproject.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom de l'entreprise est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
    private String nom;

    @NotBlank(message = "Le secteur d'activité est obligatoire")
    private String secteurActivite;

    private String logoPath;

    public @NotBlank(message = "Le nom de l'entreprise est obligatoire") @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères") String getNom() {
        return nom;
    }

    public void setNom(@NotBlank(message = "Le nom de l'entreprise est obligatoire") @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères") String nom) {
        this.nom = nom;
    }

    @OneToMany(mappedBy = "entreprise", cascade = CascadeType.ALL)
    private List<Projet> projets;

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




}
