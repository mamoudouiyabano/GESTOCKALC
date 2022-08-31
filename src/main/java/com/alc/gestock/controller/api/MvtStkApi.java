package com.alc.gestock.controller.api;

import com.alc.gestock.dto.MvtStkDto;
import com.alc.gestock.model.TypeMvtStk;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;

import static com.alc.gestock.utils.Constants.APP_ROOT;


@Api(APP_ROOT + "/mvtstk")
public interface MvtStkApi {

    @PostMapping(value = APP_ROOT + "/mvtstk/create" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un mvt de stock", notes = "permet d'enregisdtrer ou modifier un mvt de stock", response = MvtStkDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "L'objet un mvt de stock cree"),
            @ApiResponse(code = 400 , message = "L'objet un mvt de stock n'est pas valide")
    })
    MvtStkDto save(@RequestBody MvtStkDto dto);

    @GetMapping(value = APP_ROOT + "/mvtstk/filter/{type}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un mvt de stock par type", notes = "permet de chercher  un mvt de stock par type", responseContainer = "List<MvtStkDto>")
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "Le  mvt de stock a ete trouve dans la bd"),
            @ApiResponse(code = 404 , message = "aucun  mvt de stock existant avec ce type") })
    List<MvtStkDto> findByType(@PathVariable("type") TypeMvtStk typeMvtStk);

    @GetMapping(value = APP_ROOT + "/mvtstk/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un mvt de stock par Item", notes = "permet de chercher  un mvt de stock par Item", responseContainer = "List<MvtStkDto>")
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "Le  mvt de stock a ete trouve dans la bd"),
            @ApiResponse(code = 404 , message = "aucun  mvt de stock existant pour cet Item") })
    List<MvtStkDto> findByItem(@PathVariable("id") Integer id);

    @GetMapping(value = APP_ROOT + "/mvtstk/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie toutes les mvt de stock ", notes = "permet de chercher  et revoyer le liste des mvt de stock", responseContainer = "List<MvtStkDto>")
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "Le  mvt de stock a ete trouve dans la bd")})
    List<MvtStkDto> findAll();

    @GetMapping(value = APP_ROOT + "/mvtstk/delete/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Supprimer un mvt de stock", notes = "permet de supprimer  un mvt de stock par id")
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "Le mvt de stock  a ete supprime dans la bd") })
    void delete(@PathVariable("id") Integer id);

    @GetMapping(value = APP_ROOT + "/mvtstk/stockreel/{idItem}")
    BigDecimal stockReelItem(@PathVariable("idItem") Integer idItem);

    @GetMapping(value = APP_ROOT + "/mvtstk/filter/Item/{idItem}")
    List<MvtStkDto> mvtStkItem(@PathVariable("idItem") Integer idItem);

    @PostMapping(value = APP_ROOT + "/mvtstk/entree/")
    MvtStkDto entreeStock(MvtStkDto dto);

    @PostMapping(value = APP_ROOT + "/mvtstk/sortie/")
    MvtStkDto sortieStock(MvtStkDto dto);

    @PostMapping(value = APP_ROOT + "/mvtstk/correctionpos/")
    MvtStkDto correctionStockPos(MvtStkDto dto);

    @PostMapping(value = APP_ROOT + "/mvtstk/correctionneg/")
    MvtStkDto correctionStockNeg(MvtStkDto dto);
}
