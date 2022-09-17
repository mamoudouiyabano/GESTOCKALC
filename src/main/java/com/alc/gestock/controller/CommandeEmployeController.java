package com.alc.gestock.controller;

import com.alc.gestock.controller.api.CommandeEmployeApi;
import com.alc.gestock.dto.CommandeEmployeDto;
import com.alc.gestock.dto.LigneCmdEmployeDto;
import com.alc.gestock.model.EtatCommande;
import com.alc.gestock.services.CommandeEmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin("*")
public class CommandeEmployeController implements CommandeEmployeApi {

    private CommandeEmployeService commandeEmployeService;

    @Autowired
    public CommandeEmployeController(CommandeEmployeService commandeEmployeService) {
        this.commandeEmployeService = commandeEmployeService;
    }

    @Override
    public ResponseEntity<CommandeEmployeDto> save(CommandeEmployeDto dto) {
        return ResponseEntity.ok(commandeEmployeService.save(dto));
    }

    @Override
    public ResponseEntity<CommandeEmployeDto> updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
        return ResponseEntity.ok(commandeEmployeService.updateEtatCommande(idCommande,etatCommande));
    }

    @Override
    public ResponseEntity<CommandeEmployeDto> updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
        return ResponseEntity.ok(commandeEmployeService.updateQuantiteCommande(idCommande,idLigneCommande,quantite));
    }

    @Override
    public ResponseEntity<CommandeEmployeDto> updateEmploye(Integer idCommande, Integer idEmploye) {
        return ResponseEntity.ok(commandeEmployeService.updateEmploye(idCommande,idEmploye));

    }

    @Override
    public ResponseEntity<CommandeEmployeDto> updateItem(Integer idCommande, Integer idLigneCommande, Integer idItem) {
        return ResponseEntity.ok(commandeEmployeService.updateItem(idCommande,idLigneCommande,idItem));

    }

    @Override
    public ResponseEntity<CommandeEmployeDto> deleteItem(Integer idCommande, Integer idLigneCommande) {
        return ResponseEntity.ok(commandeEmployeService.deleteItem(idCommande,idLigneCommande));
    }

    @Override
    public ResponseEntity<CommandeEmployeDto> findById(Integer id) {
        return ResponseEntity.ok(commandeEmployeService.findById(id));
    }

    @Override
    public ResponseEntity<CommandeEmployeDto> findByCodeCommande(String codeCmd) {
        return ResponseEntity.ok(commandeEmployeService.findByCode(codeCmd));
    }

    @Override
    public ResponseEntity<List<LigneCmdEmployeDto>> findAllLignesCommandesEmployeByCommandeEmployeId(Integer idCommande) {
        return ResponseEntity.ok(commandeEmployeService.findAllLignesCommandesEmployeByCommandeEmployeId(idCommande));

    }

    @Override
    public ResponseEntity<List<CommandeEmployeDto>> findAll() {
        return ResponseEntity.ok(commandeEmployeService.findAll());

    }

    @Override
    public ResponseEntity<List<LigneCmdEmployeDto>> findAllLignesCmdes() {
        return ResponseEntity.ok(commandeEmployeService.findAllLignesCmdes());

    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        commandeEmployeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
