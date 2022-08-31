package com.alc.gestock.controller.api;

import com.alc.gestock.dto.CommandeEmployeDto;
import com.alc.gestock.dto.LigneCmdEmployeDto;
import com.alc.gestock.model.EtatCommande;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.alc.gestock.utils.Constants.APP_ROOT;


@Api(APP_ROOT + "/cmdemployes")
public interface CommandeEmployeApi {

    @PostMapping(value = APP_ROOT + "/cmdemployes/create" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une commande employe", notes = "permet d'enregisdtrer ou modifier une commande employe ", response = CommandeEmployeDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "L'objet commande employe cree"),
            @ApiResponse(code = 400 , message = "L'objet commande employe n'est pas valide")
    })
    ResponseEntity<CommandeEmployeDto> save(@RequestBody CommandeEmployeDto dto);

    @PatchMapping(value = APP_ROOT + "/cmdemployes/update/etat/{idCommande}/{etatCommande}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier l'etat de la commande d'un employe", notes = "permet de modifier l'etat d'une commande employe ", response = CommandeEmployeDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "L'etat de la commande a ete modifiee"),
            @ApiResponse(code = 400 , message = "L'objet commande employe n'est pas valide")
    })
    ResponseEntity<CommandeEmployeDto> updateEtatCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("etatCommande") EtatCommande etatCommande);

    @PatchMapping(value = APP_ROOT + "/cmdemployes/update/quantite/{idCommande}/{idLigneCommande}/{quantite}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier la quantite de la commande d'un employe", notes = "permet de modifier la quantite d'une commande employe ", response = CommandeEmployeDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "L'etat de la commande a ete modifiee"),
            @ApiResponse(code = 400 , message = "L'objet commande employe n'est pas valide")
    })
    ResponseEntity<CommandeEmployeDto> updateQuantiteCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("quantite") BigDecimal quantite);

    @PatchMapping(value = APP_ROOT + "/cmdemployes/update/employe/{idCommande}/{idemploye}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier  la commande d'un employe", notes = "permet de modifierla commande employe ", response = CommandeEmployeDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "L'etat de la commande a ete modifiee"),
            @ApiResponse(code = 400 , message = "L'objet commande employe n'est pas valide")
    })
    ResponseEntity<CommandeEmployeDto> updateEmploye(@PathVariable("idCommande") Integer idCommande, @PathVariable("idemploye") Integer idemploye);


    @PatchMapping(value = APP_ROOT + "/cmdemployes/update/item/{idCommande}/{idLigneCommande}/iditem" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier  l'item d'une commande", notes = "permet l'item dans une commande employe ", response = CommandeEmployeDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "L'item a ete modifiee"),
            @ApiResponse(code = 400 , message = "L'objet commande employe n'est pas valide")
    })
    ResponseEntity<CommandeEmployeDto> updateItem(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("iditem") Integer Iditem);


    @DeleteMapping(value = APP_ROOT + "/cmdemployes/delete/item/{idCommande}/{idLigneCommande}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Suppresssion d'une ligne de commande", notes = "permet de supprimer un item ou une ligne de commande ", response = CommandeEmployeDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "Suprression effectuee"),
            @ApiResponse(code = 400 , message = "L'objet commande employe n'est pas valide")
    })
    ResponseEntity<CommandeEmployeDto> deleteItem(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande);

    @GetMapping(value = APP_ROOT + "/cmdemployes/{idCmd}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une commande employe par id", notes = "permet de chercher  une commande employe par id", response = CommandeEmployeDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "La Commande employe a ete trouve dans la bd"),
            @ApiResponse(code = 404 , message = "aucune commande employe existant avec cet id") })
    ResponseEntity<CommandeEmployeDto> findById(@PathVariable("idCmd") Integer id);

    @GetMapping(value = APP_ROOT + "/cmdemployes/code/{codeCmd}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une commande employe par code", notes = "permet de chercher  une commande employe par code", response = CommandeEmployeDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "La Commande employe a ete trouve dans la bd"),
            @ApiResponse(code = 404 , message = "aucune commande employe existant avec ce code") })
    ResponseEntity<CommandeEmployeDto> findByCodeCommande(@PathVariable("codeCmd") String codeCmd);

    @GetMapping(value = APP_ROOT + "/cmdemployes/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste de tous les commandes employe", notes = "permet de chercher et de renvoyer la liste des commandes employe", responseContainer = "List<CommandeEmployeDto>")
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "La commande employe a ete trouve dans la bd") })
    ResponseEntity<List<CommandeEmployeDto> > findAll();

    @GetMapping(value = APP_ROOT + "/cmdemployes/lignesCommande/{idCommande}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher toutes les lignescommandes d'un employe par lID de sa commande", notes = "permet de chercher  toutes les lignescommandes d'un employe par lID de sa commande", response = CommandeEmployeDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "Les lignes ont ete trouvee dans la BDD"),
            @ApiResponse(code = 404 , message = "aucune commande employe existant avec ce code") })
    ResponseEntity<List<LigneCmdEmployeDto>> findAllLignesCommandesEmployeByCommandeEmployeId(@PathVariable("idCommande") Integer idCommande);

    @DeleteMapping(value = APP_ROOT + "/cmdemployes/delete/{idCmd}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Supprimer une commande employe avec son Id", notes = "permet de supprimer  une commande employe par id")
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "La commande employe a ete supprime dans la bd") })
    ResponseEntity<Void> delete(@PathVariable("idCmd") Integer id);
}
