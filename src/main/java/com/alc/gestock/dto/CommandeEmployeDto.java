package com.alc.gestock.dto;


import com.alc.gestock.model.CommandeEmploye;
import com.alc.gestock.model.EtatCommande;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Builder
@Data
public class CommandeEmployeDto {

    private Integer id;

    private String code;

    private Instant dateCommande;

    private EtatCommande etatCommande;

    private EmployeDto employe;



    private List<LigneCmdEmployeDto> ligneCmdEmployeDtos;

    public static CommandeEmployeDto fromEntity(CommandeEmploye commandeEmploye)
    {
        if (commandeEmploye == null)
        {
            return null;
        }



        return CommandeEmployeDto.builder()
                .id(commandeEmploye.getId())
                .code(commandeEmploye.getCode())
                .dateCommande(commandeEmploye.getDateCommande())
                .etatCommande(commandeEmploye.getEtatCommande())
                .employe(EmployeDto.fromEntity(commandeEmploye.getEmploye()))
//                .ligneCmdEmployeDtos(commandeEmploye.getLigneCmdEmployes() != null ?
//                        commandeEmploye.getLigneCmdEmployes().stream()
//                        .map(LigneCmdEmployeDto::fromEntity)
//                        .collect(Collectors.toList()): null)

                .build();
    }

    public static CommandeEmploye toEntity(CommandeEmployeDto commandeEmployeDto)
    {
        if (commandeEmployeDto == null)
        {
            return null;
        }

        CommandeEmploye commandeEmploye = new CommandeEmploye();
        commandeEmploye.setId(commandeEmployeDto.getId());
        commandeEmploye.setCode(commandeEmployeDto.getCode());
        commandeEmploye.setDateCommande(commandeEmployeDto.getDateCommande());
        commandeEmploye.setEtatCommande(commandeEmployeDto.getEtatCommande());
        commandeEmploye.setEmploye(EmployeDto.toEntity(commandeEmployeDto.getEmploye()));
        return commandeEmploye;
    }

    public boolean isCommandeLivree() {
        return EtatCommande.LIVREE.equals(this.etatCommande);
    }



}
