package com.alc.gestock.validator;


import com.alc.gestock.dto.EmployeDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EmployeValidator {

    public static List<String> Validate(EmployeDto employeDto)
    {
        List<String> errors = new ArrayList<>();
        if (employeDto == null)
        {
            errors.add("veuillez renseigner le nom de l'employe");
            errors.add("veuillez renseigner le prenom de l'employe");
            errors.add("veuillez renseigner le mail de l'employe");
            errors.add("veuillez renseigner le poste de l'employe");
            errors.add("veuillez renseigner la date d'embauche de l'employe");

        }
        if (!StringUtils.hasLength(employeDto.getCodeEmploye()))
        {
            errors.add("veuillez renseigner le code de l'employe");
        }
        if (!StringUtils.hasLength(employeDto.getNom()))
        {
            errors.add("veuillez renseigner le nom  l'employe");
        }
        if (!StringUtils.hasLength(employeDto.getPrenom()))
        {
            errors.add("veuillez renseigner le prenom  l'employe");
        }
        if (!StringUtils.hasLength(employeDto.getEmail()))
        {
            errors.add("veuillez renseigner le mail  l'employe");
        }
        if (!StringUtils.hasLength(employeDto.getPoste()))
        {
            errors.add("veuillez renseigner le poste de l'employe");
        }

        return errors;
    }
}
