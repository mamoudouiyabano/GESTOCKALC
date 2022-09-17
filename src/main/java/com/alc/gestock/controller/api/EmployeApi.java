package com.alc.gestock.controller.api;

import com.alc.gestock.dto.EmployeDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.alc.gestock.utils.Constants.APP_ROOT;


@Api(APP_ROOT + "/employes")
public interface EmployeApi {

    @PostMapping(value = APP_ROOT + "/employes/create" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un employe", notes = "permet d'enregistrer ou modifier un employe ", response = EmployeDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "L'objet employe cree"),
            @ApiResponse(code = 400 , message = "L'objet employe n'est pas valide")
    })
    EmployeDto save(@RequestBody EmployeDto dto);

    @PostMapping(value = APP_ROOT + "/employes/update" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un employe", notes = "permet modifier un employe ", response = EmployeDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "L'objet employe est modifie"),
            @ApiResponse(code = 400 , message = "L'objet employe n'est pas valide")
    })
    EmployeDto update(@RequestBody EmployeDto dto);

    @GetMapping(value = APP_ROOT + "/employes/{idEmploye}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un employe par id", notes = "permet de chercher  un employe par id", response = EmployeDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "Le employe a ete trouve dans la bd"),
            @ApiResponse(code = 404 , message = "aucun employe existant avec cet id") })
    EmployeDto findById(@PathVariable("idEmploye") Integer id);

    @GetMapping(value = APP_ROOT + "/employes/name/{nomEmploye}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un employe par nom", notes = "permet de chercher  un employe par nom", response = EmployeDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "Le employe a ete trouve dans la bd"),
            @ApiResponse(code = 404 , message = "aucun employe existant avec ce nom") })
    EmployeDto findByNomEmploye(@PathVariable("nomEmploye") String nomEmploye);

    @GetMapping(value = APP_ROOT + "/employes/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste de tous les employe", notes = "permet de chercher et de renvoyer la liste des employes", responseContainer = "List<EmployeDto>")
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "Le employe a ete trouve dans la bd") })
    List<EmployeDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/employes/delete/{idEmploye}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Supprimer un employe avec son Id", notes = "permet de supprimer  un employe par id")
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "Le employe a ete supprime dans la bd") })
    void delete(@PathVariable("idEmploye") Integer id);

}
