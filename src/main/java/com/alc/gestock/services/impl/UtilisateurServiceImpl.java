package com.alc.gestock.services.impl;


import com.alc.gestock.dto.ChangerMotDePasseUtilisateurDto;
import com.alc.gestock.dto.UtilisateurDto;
import com.alc.gestock.exception.EntityNotFoundException;
import com.alc.gestock.exception.ErrorCodes;
import com.alc.gestock.exception.InvalidEntityException;
import com.alc.gestock.exception.InvalidOperationException;
import com.alc.gestock.model.Utilisateur;
import com.alc.gestock.repository.UtilisateurRepository;
import com.alc.gestock.services.UtilisateurService;
import com.alc.gestock.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;


    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;

    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        List<String> errors = UtilisateurValidator.Validate(dto);
        UtilisateurDto savedUser;

        if (!errors.isEmpty()){
            log.error("User not valid");
            throw new InvalidEntityException("urilisateur not valide" , ErrorCodes.UTILISATEUR_NOT_VALID,errors);
        }

        Optional<Utilisateur> user = utilisateurRepository.findUtilisateurByEmail(dto.getEmail());
        //UtilisateurDto userDto = UtilisateurDto.fromEntity(user.get());

        if (user.isPresent())
        {

            log.warn("utilisateur existe deja");
            throw new InvalidOperationException("utilisateur existe deja",ErrorCodes.UTILISATEUR_ALREADY_EXIST);
        }

        else {
            log.warn("processing");
            savedUser = UtilisateurDto.fromEntity(utilisateurRepository.save(UtilisateurDto.toEntity(dto)));
        }

        return savedUser;

    }

    @Override
    public UtilisateurDto update(UtilisateurDto dto) {
        List<String> errors = UtilisateurValidator.Validate(dto);

        if (!errors.isEmpty()){
            log.error("User not valid");
            throw new InvalidEntityException("urilisateur not valide" , ErrorCodes.UTILISATEUR_NOT_VALID,errors);
        }

        return UtilisateurDto.fromEntity(utilisateurRepository.save(UtilisateurDto.toEntity(dto)));

    }

    @Override
    public UtilisateurDto findById(Integer id) {
        if (id == null)
        {
            log.error("id is null");
            return null;
        }
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        UtilisateurDto dto = UtilisateurDto.fromEntity(utilisateur.get());
        return Optional.of(dto).orElseThrow(()-> new EntityNotFoundException("utilisateur not found", ErrorCodes.UTILISATEUR_NOT_VALID));
    }

    @Override
    public UtilisateurDto findByNomUtilisateur(String nomUtilisateur) {
        if (!StringUtils.hasLength(nomUtilisateur))
        {
            log.error("nom Utilisateur is null");
            return null;
        }
        Optional<Utilisateur> utilisateur = utilisateurRepository.findUtilisateurByNom(nomUtilisateur);
        UtilisateurDto dto = UtilisateurDto.fromEntity(utilisateur.get());
        return Optional.of(dto).orElseThrow(()-> new EntityNotFoundException("utilisateur not found", ErrorCodes.UTILISATEUR_NOT_VALID));
    }

    @Override
    public UtilisateurDto findByEmail(String email) {
        return utilisateurRepository.findUtilisateurByEmail(email)
                .map(UtilisateurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun utilisateur avec l'email = " + email + " n ete trouve dans la BDD",
                        ErrorCodes.UTILISATEUR_NOT_FOUND
                ));
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("Id is null");
        }

        utilisateurRepository.deleteById(id);
    }

    @Override
    public UtilisateurDto changerMotDePasse(ChangerMotDePasseUtilisateurDto dto) {

        validate(dto);


        Optional<Utilisateur> utilisateurOptionnal = utilisateurRepository.findById(dto.getId());
        if(Objects.isNull(utilisateurOptionnal)) {
            log.error("Aucun utilisateur na ete trouve avec l'id "+dto.getId());
            throw new EntityNotFoundException("utilisateur not found", ErrorCodes.UTILISATEUR_NOT_VALID);
        }

        Utilisateur utilisateur = utilisateurOptionnal.get();
        utilisateur.setMotDePasse(dto.getMotDePasse());

        return UtilisateurDto.fromEntity(
                utilisateurRepository.save(utilisateur)
        );


    }

    private void validate(ChangerMotDePasseUtilisateurDto dto) {

        if(dto == null) {
            log.warn("impossible de modifier le mot de passe avec  un objet null");
            throw new InvalidOperationException("Aucune info na ete fourni pour changer le mot depasse",ErrorCodes.ITEM_NOT_FOUND.UTILISATEUR_CHANGE_PASSWORD_NOT_VALID);
        }

        if(dto.getId() == null) {
            log.warn("impossible de modifier le mot de passe avec  un id null");
            throw new InvalidOperationException("Aucun id na ete fourni pour changer le mot depasse",ErrorCodes.ITEM_NOT_FOUND.UTILISATEUR_CHANGE_PASSWORD_NOT_VALID);
        }

        if(!StringUtils.hasLength(dto.getMotDePasse()) || !StringUtils.hasLength(dto.getConfirmMotDePasse())) {
            log.warn("impossible de modifier le mot de passe car il n match pas avec confirm mot de passe");
            throw new InvalidOperationException("les deux mots de passe doivent etre identiques pour changer le mot depasse",ErrorCodes.ITEM_NOT_FOUND.UTILISATEUR_CHANGE_PASSWORD_NOT_VALID);
        }

        if(!dto.getMotDePasse().equals(dto.getConfirmMotDePasse())) {
            log.warn("impossible de modifier le mot de passe avecun mot depasse null");
            throw new InvalidOperationException("Aucun mot de passe  na ete fourni pour changer le mot depasse",ErrorCodes.ITEM_NOT_FOUND.UTILISATEUR_CHANGE_PASSWORD_NOT_VALID);
        }

    }


}
