package com.alc.gestock.repository;

import com.alc.gestock.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer > {

    Optional<Utilisateur> findUtilisateurByNom(String nomUtilisateur);

    Optional<Utilisateur> findUtilisateurByEmail(String email);
}
