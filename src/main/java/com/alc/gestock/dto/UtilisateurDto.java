package com.alc.gestock.dto;


import com.alc.gestock.model.CommandeEmploye;
import com.alc.gestock.model.Employe;
import com.alc.gestock.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Builder
@Data
public class UtilisateurDto {

    private Integer id;

    private String nom;

    private String prenom;

    private String email;

    private String motDePasse;

    private String photo;

    private String role;


    public static UtilisateurDto fromEntity(Utilisateur utilisateur)
    {
        if (utilisateur == null)
        {
            return null;
        }

        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .photo(utilisateur.getPhoto())
                .email(utilisateur.getEmail())
                .motDePasse(utilisateur.getMotDePasse())
                .role(utilisateur.getRole())
                .build();
    }



    public static Utilisateur toEntity(UtilisateurDto utilisateurDto)
    {
        if (utilisateurDto == null)
        {
            return null;
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setPhoto(utilisateurDto.getPhoto());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setMotDePasse(utilisateurDto.getMotDePasse());
        utilisateur.setRole(utilisateurDto.getRole());

        return utilisateur;

    }


}
