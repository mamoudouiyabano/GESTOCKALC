package com.alc.gestock.dto;


import com.alc.gestock.model.MvtStk;
import com.alc.gestock.model.SourceMvtStk;
import com.alc.gestock.model.TypeMvtStk;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Data
public class MvtStkDto {

    private Integer id;



    private Instant dateMvt;


    private BigDecimal quantite;


    private ItemDto item;


    private TypeMvtStk typeMvt;

    private SourceMvtStk sourceMvtStk;

    private Integer idEntreprise;

    public static MvtStkDto fromEntity(MvtStk mvtStk)
    {
        if (mvtStk == null)
        {
            return null;
        }

        return  MvtStkDto.builder()
                .id(mvtStk.getId())
                .dateMvt(mvtStk.getDateMvt())
                .quantite(mvtStk.getQuantite())
                .item(ItemDto.fromEntity(mvtStk.getItem()))
                .typeMvt(mvtStk.getTypeMvt())
                .sourceMvtStk(mvtStk.getSourceMvtStk())
                .build();

    }

    public static MvtStk toEntity(MvtStkDto mvtStkDto)
    {
        if (mvtStkDto == null)
        {
            return null;
        }

        MvtStk mvtStk = new  MvtStk();
        mvtStk.setId(mvtStkDto.getId());
        mvtStk.setDateMvt(mvtStkDto.getDateMvt());
        mvtStk.setQuantite(mvtStkDto.getQuantite());
        mvtStk.setTypeMvt(mvtStkDto.getTypeMvt());
        mvtStk.setSourceMvtStk(mvtStkDto.getSourceMvtStk());
        mvtStk.setItem(ItemDto.toEntity(mvtStkDto.getItem()));
        return mvtStk;
    }
}
