package com.example.saif.saifproject.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le titre est obligatoire")
    @Size(min = 3, max = 100, message = "Le titre doit contenir entre 3 et 100 caractères")
    private String titre;

    @Size(max = 500, message = "La description ne peut pas dépasser 500 caractères")
    private String description;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    @ManyToOne
    @JoinColumn(name = "entreprise_id")
    private Entreprise entreprise;

    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)
    private List<Tache> taches;
    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public @Size(max = 500, message = "La description ne peut pas dépasser 500 caractères") String getDescription() {
        return description;
    }

    public void setDescription(@Size(max = 500, message = "La description ne peut pas dépasser 500 caractères") String description) {
        this.description = description;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Ajoutez le getter pour le titre
    public String getTitre() {
        return this.titre;
    }
}
