package com.alc.gestock.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;

@NoArgsConstructor
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="utilisateur")
public class Utilisateur extends  AbstractEntity{

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "codeEmploye")
    private String codeEmploye;

    @Column(name = "motdepasse")
    private String motDePasse;


    @Column(name = "dateembauche")
    private String dateEmbauche;

    @Column(name = "photo")
    private String photo;

    @Column(name = "role")
    private String role;
}
