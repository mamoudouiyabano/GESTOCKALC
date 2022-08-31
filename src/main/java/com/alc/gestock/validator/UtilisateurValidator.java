package com.alc.gestock.validator;


import com.alc.gestock.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {

    public static List<String> Validate(UtilisateurDto utilisateurDto)
    {
        List<String> errors = new ArrayList<>();
        if (utilisateurDto == null)
        {
            errors.add("veuillez renseigner le nom de l'utilisateur");
            errors.add("veuillez renseigner le prenom de l'utilisateur");
            errors.add("veuillez renseigner le mot de passe de l'utilisateur");
            errors.add("veuillez renseigner l'email de l'utilisateur");
            errors.add("veuillez renseigner le role de l'utilisateur");

        }else{
            if (!StringUtils.hasLength(utilisateurDto.getNom()))
            {
                errors.add("veuillez renseigner le nom de l'utilisateur");
            }
            if (!StringUtils.hasLength(utilisateurDto.getPrenom()))
            {
                errors.add("veuillez renseigner le prenom de l'utilisateur");
            }
            if (!StringUtils.hasLength(utilisateurDto.getMotDePasse()))
            {
                errors.add("veuillez renseigner le mot de passe de l'utilisateur");
            }
            if (utilisateurDto.getEmail() == null)
            {
                errors.add("veuillez renseigner l'email de l'utilisateur");

            }
            if (utilisateurDto.getRole() == null)
            {
                errors.add("veuillez renseigner le role de l'utilisateur");

            }
        }


        return errors;
    }
}
