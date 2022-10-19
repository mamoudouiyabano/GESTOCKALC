package com.alc.gestock.validator;


import com.alc.gestock.dto.CommandeEmployeDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeEmployeValidator {

    public static List<String> Validate(CommandeEmployeDto commandeEmployeDto)
    {
        List<String> errors = new ArrayList<>();

        if (commandeEmployeDto == null)
        {
            errors.add("Veuillez renseigner le code de la commande");
            errors.add("Veuillez renseigner la date de la commande");
            errors.add("Veuillez renseigner l'id de l'employe de qui passe la cmde");

        }

        if (!StringUtils.hasLength(commandeEmployeDto.getCode()))
        {
            errors.add("Veuillez renseigner le code de la commande");
        }

//        if (commandeEmployeDto.getDateCommande() == null)
//        {
//            errors.add("Veuillez renseigner la date de la commande");
//        }

        if (commandeEmployeDto.getEmploye().getId() == null)
        {
            errors.add("Veuillez renseigner l'id de l'employe de qui passe la cmde");
        }

        return errors;
    }

}
