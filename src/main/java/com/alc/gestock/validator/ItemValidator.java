package com.alc.gestock.validator;


import com.alc.gestock.dto.ItemDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ItemValidator {

    public static List<String> validate(ItemDto itemDto)
    {
        List<String> errors = new ArrayList<>();

        if (itemDto == null)
        {
            errors.add("Veuillez renseiger le code de l'item");
            errors.add("Veuillez renseiger la designation de l'item");
            errors.add("Veuillez renseiger le PU HT de l'item");
            errors.add("Veuillez renseiger le Taux TVA de l'item");
            errors.add("Veuillez renseiger le Prix TTC de l'item");
            errors.add("Veuillez selectionner une categorie de l'item");
        }
        if (!StringUtils.hasLength(itemDto.getCodeItem()))
        {
            errors.add("Veuillez renseiger le code de l'item");
        }
        if (!StringUtils.hasLength(itemDto.getDesignation()))
        {
            errors.add("Veuillez renseiger la designation de l'item");
        }
        if (itemDto.getPrixUnitaireHt() == null)
        {
            errors.add("Veuillez renseiger le PU HT de l'item");
        }
//        if (itemDto.getPrixUnitaireTtc() == null)
//        {
//            errors.add("Veuillez renseiger le PU TTC de l'item");
//        }

        if (itemDto.getCategory() == null)
        {
            errors.add("Veuillez selectionner une categorie de l'item");
        }

        return errors;
    }
}
