package com.alc.gestock.dto;

import com.alc.gestock.model.CommandeEmploye;
import com.alc.gestock.model.Employe;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Builder
@Data

public class EmployeDto {


    private Integer id;

    private String nom;

    private String prenom;

    private String email;

    private String photo;

    private String codeEmploye;

    private String poste;

    private Instant dateEmbauche;

    @JsonIgnore
    private List<CommandeEmploye> commandeEmployes;


    public static EmployeDto fromEntity(Employe employe)
    {
        if (employe == null)
        {
            return null;
        }

        return EmployeDto.builder()
                .id(employe.getId())
                .nom(employe.getNom())
                .prenom(employe.getPrenom())
                .photo(employe.getPhoto())
                .email(employe.getEmail())
                .codeEmploye(employe.getCodeEmploye())
                .poste(employe.getPoste())
                .dateEmbauche(employe.getDateEmbauche())
                .build();
    }

    public static Employe toEntity(EmployeDto employeDto)
    {
        if (employeDto == null)
        {
            return null;
        }

        Employe employe = new Employe();
        employe.setId(employeDto.getId());
        employe.setNom(employeDto.getNom());
        employe.setPrenom(employeDto.getPrenom());
        employe.setPhoto(employeDto.getPhoto());
        employe.setEmail(employeDto.getEmail());
        employe.setCodeEmploye(employeDto.getCodeEmploye());
        employe.setPoste(employeDto.getPoste());
        employe.setDateEmbauche(employeDto.getDateEmbauche());
        return employe;

    }


}

