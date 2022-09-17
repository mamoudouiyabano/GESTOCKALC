package com.alc.gestock.services.impl;

import com.alc.gestock.dto.*;
import com.alc.gestock.exception.EntityNotFoundException;
import com.alc.gestock.exception.ErrorCodes;
import com.alc.gestock.exception.InvalidEntityException;
import com.alc.gestock.exception.InvalidOperationException;
import com.alc.gestock.model.*;
import com.alc.gestock.repository.*;
import com.alc.gestock.services.CommandeEmployeService;
import com.alc.gestock.services.MvtStkService;
import com.alc.gestock.validator.CommandeEmployeValidator;
import com.alc.gestock.validator.ItemValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeEmployeServiceImpl implements CommandeEmployeService {

    private CommandeEmployeRepository commandeEmployeRepository;
    private EmployeRepository employeRepository;
    private ItemRepository itemRepository;
    private LigneCommandeEmployeRepository ligneCommandeEmployeRepository;
    private MvtStkService mvtStkService;

    @Autowired
    public CommandeEmployeServiceImpl(CommandeEmployeRepository commandeEmployeRepository, EmployeRepository employeRepository, ItemRepository itemRepository, LigneCommandeEmployeRepository ligneCommandeEmployeRepository, MvtStkService mvtStkService) {
        this.commandeEmployeRepository = commandeEmployeRepository;
        this.employeRepository = employeRepository;
        this.itemRepository = itemRepository;
        this.ligneCommandeEmployeRepository = ligneCommandeEmployeRepository;
        this.mvtStkService = mvtStkService;
    }


    @Override
    public CommandeEmployeDto save(CommandeEmployeDto dto) {
        List<String> errors = CommandeEmployeValidator.Validate(dto);

        if (!errors.isEmpty()){
            log.error("Commande Employe n'est pas valide");
            throw new InvalidEntityException("la commande du Employe n'est  valide", ErrorCodes.COMMANDE_EMPLOYE_NOT_VALID,errors);

        }

        //en cas de modification
        if(dto.getId() != null && dto.isCommandeLivree()) {
            throw new InvalidOperationException("Cette operation n peut etre effectuee sur la commande"+dto.getId()+"car elle est deja livree",ErrorCodes.COMMANDE_EMPLOYE_NON_MODIFIABLE);
        }

        Optional<Employe> Employe = employeRepository.findById(dto.getEmploye().getId());
        if (Employe == null){
            log.warn("Employe with ID  {} was not found in the DB",dto.getEmploye().getId());
            throw new EntityNotFoundException("Aucun Employe avec L'ID"+dto.getEmploye().getId()+"n'a ete trouvé",ErrorCodes.EMPLOYE_NOT_FOUND);
        }

        List<String> itemErrors = new ArrayList<>();

        if(dto.getLigneCmdEmployeDtos() != null){
            dto.getLigneCmdEmployeDtos().forEach(ligCmdEmpl->{
                if (ligCmdEmpl.getItem() != null){
                    Optional<Item> item = itemRepository.findById(ligCmdEmpl.getItem().getId());
                    if (item == null)
                    {
                        itemErrors.add("L'item avec l'id "+ ligCmdEmpl.getItem().getId() + " n'existe pas");
                    }
                }else { itemErrors.add("Impossible de passer une commande avec un item null");
                }
            });

        }

        if (!itemErrors.isEmpty())
        {
            log.warn("");
            throw new InvalidEntityException("item n'existe pas dans la bdd",ErrorCodes.ITEM_NOT_FOUND,itemErrors);
        }

        CommandeEmploye savedCmdEmpl = commandeEmployeRepository.save(CommandeEmployeDto.toEntity(dto));

        if (dto.getLigneCmdEmployeDtos() != null){
            dto.getLigneCmdEmployeDtos().forEach(ligCmdEmpl->{
                LigneCmdEmploye ligneCommandeEmploye = LigneCmdEmployeDto.toEntity(ligCmdEmpl);
                ligneCommandeEmploye.setCommandeEmploye(savedCmdEmpl);
                ligneCommandeEmployeRepository.save(ligneCommandeEmploye);

            });
        }

        // update mvt mvt stock
        updateMvtStk(savedCmdEmpl.getId());
        return CommandeEmployeDto.fromEntity(savedCmdEmpl);
    }



    @Override
    public CommandeEmployeDto findById(Integer id) {

        if (id == null){
            log.error("L'ID  de la commande est NULL");
            return null;
        }
        return commandeEmployeRepository.findById(id)
                .map(CommandeEmployeDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("Aucune commande trouve avec l'id "+id,ErrorCodes.COMMANDE_EMPLOYE_NOT_FOUND));
    }

    @Override
    public CommandeEmployeDto findByCode(String codeCommandeEmploye) {
        if (!StringUtils.hasLength(codeCommandeEmploye)){
            log.error("Le code  de la commande est NULL");
            return null;
        }

        return commandeEmployeRepository.findCommandeEmployeByCode(codeCommandeEmploye)
                .map(CommandeEmployeDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("Aucun item trouve avec le code  "+codeCommandeEmploye,ErrorCodes.COMMANDE_EMPLOYE_NOT_FOUND));

    }

    @Override
    public List<CommandeEmployeDto> findAll() {
        return commandeEmployeRepository.findAll().stream()
                .map(CommandeEmployeDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<LigneCmdEmployeDto> findAllLignesCmdes() {
        return ligneCommandeEmployeRepository.findAll().stream()
                .map(LigneCmdEmployeDto::fromEntity)
                .collect(Collectors.toList());
    }





    @Override
    public List<LigneCmdEmployeDto> findAllLignesCommandesEmployeByCommandeEmployeId(Integer idCommande) {
        return ligneCommandeEmployeRepository.findAllByCommandeEmployeId(idCommande).stream()
                .map(LigneCmdEmployeDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CommandeEmployeDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
        checkIdCommande(idCommande);
        if(!StringUtils.hasLength(String.valueOf(etatCommande))) {
            log.error("L'etat de la commande Employe est null");
            throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec etat null", ErrorCodes.COMMANDE_EMPLOYE_NON_MODIFIABLE);

        }

        CommandeEmployeDto commandeEmploye = checkEtatCommande(idCommande);
        commandeEmploye.setEtatCommande(etatCommande);

        CommandeEmploye savedCmdEmpl = commandeEmployeRepository.save(CommandeEmployeDto.toEntity(commandeEmploye));
        if(commandeEmploye.isCommandeLivree()) {
            updateMvtStk(idCommande);

        }
        return CommandeEmployeDto.fromEntity(
                savedCmdEmpl
        );
    }



    @Override
    public CommandeEmployeDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {

        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);

        if(quantite  == null || quantite.compareTo(BigDecimal.ZERO)==0) {
            log.error("la quantite de commande est null");
            throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec une quantite null ou ZERO", ErrorCodes.COMMANDE_EMPLOYE_NON_MODIFIABLE);

        }

        CommandeEmployeDto commandeEmploye = checkEtatCommande(idCommande);

        Optional<LigneCmdEmploye> ligneCommandeEmployeOptional = findLigneCommandeEmploye(idLigneCommande);

        LigneCmdEmploye ligneCommandeEmploye = ligneCommandeEmployeOptional.get();
        ligneCommandeEmploye.setQuantite(quantite);
        ligneCommandeEmployeRepository.save(ligneCommandeEmploye);

        return commandeEmploye;

    }

    private Optional<LigneCmdEmploye> findLigneCommandeEmploye(Integer idLigneCommande) {

        Optional<LigneCmdEmploye> ligneCommandeEmployeOptional =ligneCommandeEmployeRepository.findById(idLigneCommande);

        if(Objects.isNull(ligneCommandeEmployeOptional)) {
            throw new EntityNotFoundException("Aucune ligne de commande Employe n'a ete trouve avec l'ID" +idLigneCommande, ErrorCodes.COMMANDE_EMPLOYE_NOT_FOUND);
        }
        return ligneCommandeEmployeOptional;
    }

    @Override
    public CommandeEmployeDto updateEmploye(Integer idCommande, Integer idEmploye) {
        checkIdCommande(idCommande);
        if(idEmploye == null) {
            log.error("L'ID de la ligne Employe est null");
            throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un ID Employe null", ErrorCodes.COMMANDE_EMPLOYE_NON_MODIFIABLE);
        }

        CommandeEmployeDto commandeEmploye = checkEtatCommande(idCommande);

        Optional<Employe> EmployeOptional = employeRepository.findById(idEmploye);
        if(Objects.isNull(EmployeOptional)) {
            throw new EntityNotFoundException("Aucun  Employe n'a ete trouve avec l'ID" +idEmploye, ErrorCodes.COMMANDE_EMPLOYE_NOT_FOUND);
        }

        commandeEmploye.setEmploye(EmployeDto.fromEntity(EmployeOptional.get()));

        return CommandeEmployeDto.fromEntity(
                commandeEmployeRepository.save(CommandeEmployeDto.toEntity(commandeEmploye))
        );

    }



    @Override
    public CommandeEmployeDto updateItem(Integer idCommande, Integer idLigneCommande, Integer idItem) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
//       checkIditem(oldIditem,"ancien");
        checkIditem(idItem,"nouvel");

        CommandeEmployeDto commandeEmploye = checkEtatCommande(idCommande);

        Optional<LigneCmdEmploye> ligneCommandeEmploye = findLigneCommandeEmploye(idLigneCommande);

        Optional<Item> itemOptional = itemRepository.findById(idItem);
        if (Objects.isNull(itemOptional)) {
            throw new EntityNotFoundException("Aucun  item n'a ete trouve avec l'ID" +idItem, ErrorCodes.ITEM_NOT_FOUND);
        }

        List<String> errors =  ItemValidator.validate(ItemDto.fromEntity(itemOptional.get()));
        if(!errors.isEmpty()) {
            throw new InvalidEntityException("item invalid", ErrorCodes.ITEM_NOT_VALID);
        }

        LigneCmdEmploye ligneCommandeEmployeToSaved = ligneCommandeEmploye.get();
        ligneCommandeEmployeToSaved.setItem(itemOptional.get());
        ligneCommandeEmployeRepository.save(ligneCommandeEmployeToSaved);

        return  commandeEmploye;



    }

    @Override
    public CommandeEmployeDto deleteItem(Integer idCommande, Integer idLigneCommande) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);

        CommandeEmployeDto commandeEmploye = checkEtatCommande(idCommande);

        //just to check the LigneCommandeEmploye and inform the Employe in case it is absent
        findLigneCommandeEmploye(idLigneCommande);

        ligneCommandeEmployeRepository.deleteById(idLigneCommande);

        return commandeEmploye;


    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("L'ID  de la commande est NULL");
        }

        List<LigneCmdEmploye> ligneCommandeEmployes = ligneCommandeEmployeRepository.findAllByCommandeEmployeId(id);
        if(!ligneCommandeEmployes.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer une cmd Employe ", ErrorCodes.COMMANDE_EMPLOYE__ALREADY_IN_USE);
        }

        commandeEmployeRepository.deleteById(id);
    }

    private void checkIdCommande(Integer idCommande) {
        if(idCommande == null) {
            log.error("Commande Employe ID us null");
            throw new InvalidOperationException("Impossible de modifier l'etat de la commande car ID null", ErrorCodes.COMMANDE_EMPLOYE_NON_MODIFIABLE);
        }
    }

    public void checkIdLigneCommande(Integer idLigneCommande) {
        if(idLigneCommande == null) {
            log.error("L'ID de la ligne Employe est null");
            throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec une ligne de commande null", ErrorCodes.COMMANDE_EMPLOYE_NON_MODIFIABLE);

        }
    }

    public void checkIditem(Integer iditem, String msg) {
        if(iditem == null) {
            log.error("L'ID de "+ msg+ "item est null");
            throw new InvalidOperationException("Impossible de modifier l'item avec l'id "+ msg + "ID item" , ErrorCodes.COMMANDE_EMPLOYE_NON_MODIFIABLE);

        }
    }

    private CommandeEmployeDto checkEtatCommande(Integer idCommande) {
        CommandeEmployeDto commandeEmploye = findById(idCommande);
        if (commandeEmploye.isCommandeLivree()) {
            throw new InvalidOperationException("Impossible de modifier l'etat de la lorsquelle est livree", ErrorCodes.COMMANDE_EMPLOYE_NON_MODIFIABLE);
        }
        return commandeEmploye;
    }

    private void updateMvtStk(Integer idCommande) {
        List<LigneCmdEmploye> ligneCommandeEmployes = ligneCommandeEmployeRepository.findAllByCommandeEmployeId(idCommande);
        ligneCommandeEmployes.forEach(lig ->  {
            MvtStkDto mvtStkDto = MvtStkDto.builder()
                    .item(ItemDto.fromEntity(lig.getItem()))
                    .dateMvt(Instant.now())
                    .typeMvt(TypeMvtStk.SORTIE)
                    .sourceMvtStk(SourceMvtStk.COMMANDE_EMPLOYE)
                    .quantite(lig.getQuantite())

                    .build();
            mvtStkService.sortieStock(mvtStkDto);
        });
    }



}
