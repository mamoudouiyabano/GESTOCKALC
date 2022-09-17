package com.alc.gestock.services;


import com.alc.gestock.dto.ChangerMotDePasseUtilisateurDto;
import com.alc.gestock.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

    UtilisateurDto save(UtilisateurDto dto);

    UtilisateurDto update(UtilisateurDto dto);


    UtilisateurDto findById(Integer id);


    UtilisateurDto findByNomUtilisateur(String nomUtilisateur);

    UtilisateurDto findByEmail(String email);


    List<UtilisateurDto> findAll();

    UtilisateurDto changerMotDePasse(ChangerMotDePasseUtilisateurDto dto);


    void delete(Integer id);

}
