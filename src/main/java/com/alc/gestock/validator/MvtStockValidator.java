package com.alc.gestock.validator;


import com.alc.gestock.dto.MvtStkDto;

import java.util.ArrayList;
import java.util.List;

public class MvtStockValidator {

    public static List<String> Validate(MvtStkDto mvtStkDto)
    {
        List<String> errors = new ArrayList<>();

        if (mvtStkDto == null)
        {
            errors.add("Veuillez renseigner la date du mouvement");
            errors.add("Veuillez renseigner la quantite d'item");
            errors.add("Veuillez renseigner l'item");
            errors.add("Veuillez renseigner le type de mvt");

        }

        if (mvtStkDto.getItem() == null)
        {
            errors.add("Veuillez renseigner l'item dont le stock a changer");
        }

        if (mvtStkDto.getDateMvt() == null)
        {
            errors.add("Veuillez renseigner la date du mvt");
        }

        if (mvtStkDto.getQuantite() == null)
        {
            errors.add("Veuillez renseigner la qte du mvt");
        }


        if (mvtStkDto.getTypeMvt() == null)
        {
            errors.add("Veuillez renseigner la type du mvt");
        }
        return errors;
    }
}
