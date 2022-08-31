package com.alc.gestock.controller.api;

import com.alc.gestock.dto.CategoryDto;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.alc.gestock.utils.Constants.APP_ROOT;


@Api(APP_ROOT + "/categories")
public interface CategoryApi {

    @PostMapping(value = APP_ROOT + "/categories/create" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une categorie", notes = "permet d'enregisdtrer ou modifier une categorie", response = CategoryDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "L'objet categorie cree"),
            @ApiResponse(code = 400 , message = "L'objet categorie n'est pas valide")
    })
    CategoryDto save(@RequestBody CategoryDto dto);

    @GetMapping(value = APP_ROOT + "/categories/{idCategory}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une categorie par id", notes = "permet de chercher  une categorie par id", response = CategoryDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "La categorie a ete trouve dans la bd"),
            @ApiResponse(code = 404 , message = "aucune categorie existant avec cet id") })
    CategoryDto findById(@PathVariable("idCategory") Integer id);

    @GetMapping(value = APP_ROOT + "/categories/code/{codeCategory}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une categorie par code", notes = "permet de chercher  une categorie par code", response = CategoryDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "La categorie a ete trouve dans la bd"),
            @ApiResponse(code = 404 , message = "aucune categorie existant avec ce code") })
    CategoryDto findByCodeCategory(@ApiParam(value = "Accept values CAT CAT11 CAT2 CAT3") @PathVariable("codeCategory") String codeCategory);

    @GetMapping(value = APP_ROOT + "/categories/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste de toutes les categories", notes = "permet de chercher et de renvoyer la liste des categories", responseContainer = "List<CategoryDto>")
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "La categorie a ete trouve dans la bd") })
    List<CategoryDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/categories/delete/{idCategory}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Supprimer une categorie avec sin Id", notes = "permet de supprimer  une categorie par id")
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "La categorie a ete supprime dans la bd") })
    void delete(@PathVariable("idCategory") Integer id);
}
