package com.alc.gestock.dto;



import com.alc.gestock.model.Item;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class ItemDto {


    private Integer id;

    private String codeItem;

    private String designation;

    private BigDecimal prixUnitaireHt;

    private BigDecimal tauxTva;

    private BigDecimal prixUnitaireTtc;

    private CategoryDto category;



    public static ItemDto fromEntity(Item item)
    {
        if (item == null)
        {
            return null;
        }

        return ItemDto.builder()
                .id(item.getId())
                .codeItem(item.getCodeItem())
                .designation(item.getDesignation())
                .prixUnitaireHt(item.getPrixUnitaireHt())
                .prixUnitaireTtc(item.getPrixUnitaireTtc())
                .tauxTva(item.getTauxTva())
                .category(CategoryDto.fromEntity(item.getCategory()))
                .build();
    }

    public static Item toEntity(ItemDto itemDto)
    {
        if (itemDto == null)
        {
            return null;
        }

        Item item = new Item();
        item.setId(itemDto.getId());
        item.setCodeItem(itemDto.getCodeItem());
        item.setDesignation(itemDto.getDesignation());
        item.setPrixUnitaireHt(itemDto.getPrixUnitaireHt());
        item.setPrixUnitaireTtc(itemDto.getPrixUnitaireTtc());
        item.setTauxTva(itemDto.getTauxTva());
        item.setCategory(CategoryDto.toEntity(itemDto.getCategory()));

        return item;
    }
}
