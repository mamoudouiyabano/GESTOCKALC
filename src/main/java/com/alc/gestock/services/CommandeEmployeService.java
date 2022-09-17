package com.alc.gestock.services;

import com.alc.gestock.dto.CommandeEmployeDto;
import com.alc.gestock.dto.LigneCmdEmployeDto;
import com.alc.gestock.model.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeEmployeService {

    CommandeEmployeDto save(CommandeEmployeDto dto);

    CommandeEmployeDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande);

    CommandeEmployeDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

    CommandeEmployeDto updateEmploye(Integer idCommande, Integer idEmploye);

    CommandeEmployeDto updateItem(Integer idCommande, Integer idLigneCommande, Integer IdItem);

    // Delete item ==> delete LigneCommandeItem
    CommandeEmployeDto deleteItem(Integer idCommande, Integer idLigneCommande);

    CommandeEmployeDto findById(Integer id);

    CommandeEmployeDto findByCode(String codeCommandeEmploye);

    List<CommandeEmployeDto> findAll();

    List<LigneCmdEmployeDto> findAllLignesCmdes();

    List<LigneCmdEmployeDto> findAllLignesCommandesEmployeByCommandeEmployeId(Integer idCommande);


    void delete(Integer id);
}
