package com.alc.gestock.controller.api;


import com.alc.gestock.dto.ChangerMotDePasseUtilisateurDto;
import com.alc.gestock.dto.UtilisateurDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.alc.gestock.utils.Constants.APP_ROOT;


@Api(APP_ROOT + "/utilisateurs")
public interface UtilisateurApi {

    @PostMapping(value = APP_ROOT + "/utilisateurs/create" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un utilisateur", notes = "permet d'enregisdtrer ou modifier un utilisateur", response = UtilisateurDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "L'objet utilisateur cree"),
            @ApiResponse(code = 400 , message = "L'objet utilisateur n'est pas valide")
    })
    UtilisateurDto save(@RequestBody UtilisateurDto dto);

    @PostMapping(value = APP_ROOT + "/utilisateurs/changermotdepasse" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Changer le mot de passe d'un utilisateur", notes = "permet de modifier le mot de passe d'un utilisateur", response = UtilisateurDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "Mot de passe modifie "),
            @ApiResponse(code = 400 , message = "L'objet utilisateur n'est pas valide")
    })
    UtilisateurDto changerMotDePasse(ChangerMotDePasseUtilisateurDto dto);

    @GetMapping(value = APP_ROOT + "/utilisateurs/{idUtilisateur}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un utilisateur par id", notes = "permet de chercher  un utilisateur par id", response = UtilisateurDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "L'utilisateur a ete trouve dans la bd"),
            @ApiResponse(code = 404 , message = "aucun utilisateur existant avec cet id") })
    UtilisateurDto findById(@PathVariable("idUtilisateur") Integer id);


    @GetMapping(value = APP_ROOT + "/utilisateurs/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi liste des utilisateurs", notes = "permet de chercher et renvoyer la liste des utilisateurs", responseContainer = "List<UtilisateurDto>")
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "la liste des utilisateurs") })
    List<UtilisateurDto> findAll();

    @GetMapping(value = APP_ROOT + "/utilisateurs/delete/{idUtilisateur}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Supprimer un utilisateur", notes = "permet de supprimer  un utilisateur par id")
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "L'utilisateur a ete supprime dans la bd") })
    void delete(@PathVariable("idUtilisateur") Integer id);
}
