package com.alc.gestock.controller.api;

import com.alc.gestock.dto.ItemDto;
import com.alc.gestock.dto.LigneCmdEmployeDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.alc.gestock.utils.Constants.APP_ROOT;


@Api(APP_ROOT + "/items")
public interface ItemApi {


    @PostMapping(value = APP_ROOT + "/items/create" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un item", notes = "permet d'enregisdtrer ou modifier un item", response = ItemDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "L'objet item cree"),
            @ApiResponse(code = 400 , message = "L'objet item n'est pas valide")
    })
    ItemDto save(@RequestBody ItemDto dto);

    @GetMapping(value = APP_ROOT + "/items/{idItem}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un item par id", notes = "permet de chercher  un item par id", response = ItemDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "L'item a ete trouve dans la bd"),
            @ApiResponse(code = 404 , message = "aucun item existant avec cet id") })
    ItemDto findById(@PathVariable("idItem") Integer id);

    @GetMapping(value = APP_ROOT + "/items/code/{codeItem}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un item par code", notes = "permet de chercher  un item par code", response = ItemDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "L'item a ete trouve dans la bd"),
            @ApiResponse(code = 404 , message = "aucun item existant avec ce code") })
    ItemDto findByCodeitem(@PathVariable("codeItem") String codeitem);

    @GetMapping(value = APP_ROOT + "/items/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi liste des item", notes = "permet de chercher et renvoyer la liste des items", responseContainer = "List<ItemDto>")
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "la liste des items") })
    List<ItemDto> findAll();

    @GetMapping(value = APP_ROOT + "/items/historique/commandeemploye/{iditem}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi lhistorique des commandes employe", notes = "permet de renvoyer la liste des commandes employe", responseContainer = "List<ItemDto>")
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "historique  commande employe") })
    List<LigneCmdEmployeDto> findHistoriqueCommandeEmploye(@PathVariable("idItem") Integer iditem);


    @GetMapping(value = APP_ROOT + "/items/filter/category/{idCategory}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi les items filtre par category", notes = "\"Renvoi les items filtre par category", responseContainer = "List<ItemDto>")
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "item par category vente") })
    List<ItemDto> findAllItemByIdCategory(@PathVariable("idCategory") Integer idCategory);

    @DeleteMapping(value = APP_ROOT + "/items/delete/{iditem}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Supprimer un item", notes = "permet de supprimer  un item par id")
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "L'item a ete supprime dans la bd") })
    void delete(@PathVariable("iditem") Integer id);

}
