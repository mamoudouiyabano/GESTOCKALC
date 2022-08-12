package com.alc.gestock.validator;



import com.alc.gestock.dto.LigneCmdEmployeDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class LigneCommandeEmployeValidator {

    public static List<String> Validate(LigneCmdEmployeDto ligneCmdEmployeDto)
    {
        List<String> errors = new ArrayList<>();
        if (ligneCmdEmployeDto == null)
        {
            errors.add("veuillez renseigner la quantite de la commande");
            errors.add("veuillez renseigner le prix  de la commmande");
            errors.add("L'id de la commande ne doit pas etre null");
            errors.add("L'id de la l'item ne doit pas etre null");


        }

        if (ligneCmdEmployeDto.getQuantite()== null)
        {
            errors.add("veuillez renseigner une quantite superieure a 0");
        }
        if (ligneCmdEmployeDto.getPrixUnitaire() == null)
        {
            errors.add("veuillez renseigner un prix superieure a 0");
        }
        if (ligneCmdEmployeDto.getCommandeEmploye() == null)
        {
            errors.add("L'id de la commande ne doit pas etre null");
        }
        if (ligneCmdEmployeDto.getItem() == null)
        {
            errors.add("L'id de la l'item ne doit pas etre null");
        }


        return errors;


    }
}
