package com.alc.gestock.services.auth;

import com.alc.gestock.dto.UtilisateurDto;
import com.alc.gestock.model.auth.ExtendedUser;
import com.alc.gestock.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurService service;

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//        System.out.println("enter here");
//       Utilisateur utilisateur = repository.findUtilisateurByEmail(email).orElseThrow(()->
//               new EntityNotFoundException("Aucun utilisateur avec l'email fourni", ErrorCodes.UTILISATEUR_NOT_FOUND));
//
//        return new User(utilisateur.getEmail(),utilisateur.getMotDePasse(), Collections.emptyList());
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

//        UtilisateurDto utilisateur = service.findByEmail(email);
        UtilisateurDto utilisateur = service.findByEmail(email);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//

        return new ExtendedUser(utilisateur.getEmail(), utilisateur.getMotDePasse(),authorities);

    }
}
