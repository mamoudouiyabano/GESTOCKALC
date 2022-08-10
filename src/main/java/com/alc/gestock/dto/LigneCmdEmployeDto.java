package com.alc.gestock.dto;

import com.alc.gestock.model.LigneCmdEmploye;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class LigneCmdEmployeDto {

    private Integer id;

    private ItemDto item;

    @JsonIgnore
    private CommandeEmployeDto commandeEmploye;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    public static LigneCmdEmployeDto fromEntity(LigneCmdEmploye ligneCmdEmploye)
    {
        if (ligneCmdEmploye == null)
        {
            return  null;
        }
        return LigneCmdEmployeDto.builder()
                .id(ligneCmdEmploye.getId())
                .item(ItemDto.fromEntity(ligneCmdEmploye.getItem()))
                .quantite(ligneCmdEmploye.getQuantite())
                .prixUnitaire(ligneCmdEmploye.getPrixUnitaire())
                .commandeEmploye(CommandeEmployeDto.fromEntity(ligneCmdEmploye.getCommandeEmploye()))

                .build();
    }

    public static LigneCmdEmploye toEntity(LigneCmdEmployeDto ligneCmdEmployeDto)
    {
        if (ligneCmdEmployeDto == null)
        {
            return null;
        }
        LigneCmdEmploye ligneCmdEmploye = new LigneCmdEmploye();
        ligneCmdEmploye.setId(ligneCmdEmployeDto.getId());
        ligneCmdEmploye.setItem(ItemDto.toEntity(ligneCmdEmployeDto.getItem()));
        ligneCmdEmploye.setPrixUnitaire(ligneCmdEmployeDto.getPrixUnitaire());
        ligneCmdEmploye.setQuantite(ligneCmdEmployeDto.getQuantite());
        ligneCmdEmploye.setCommandeEmploye(CommandeEmployeDto.toEntity(ligneCmdEmployeDto.getCommandeEmploye()));
        return ligneCmdEmploye;
    }



}
