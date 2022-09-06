package com.alc.gestock.services.impl;

import com.alc.gestock.dto.CommandeEmployeDto;
import com.alc.gestock.dto.EmployeDto;
import com.alc.gestock.dto.ItemDto;
import com.alc.gestock.dto.LigneCmdEmployeDto;
import com.alc.gestock.model.EtatCommande;
import com.alc.gestock.services.CommandeEmployeService;
import com.alc.gestock.validator.ItemValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Array;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
class CommandeEmployeServiceImplTest {

    @Autowired
    private CommandeEmployeService service;

    @Test
    public void shouldCreateEmployeTransactionWithSuccess() {

        EmployeDto employe = EmployeDto.builder()
                .id(19)
                .build();

        ItemDto item = ItemDto.builder()
                .id(17)
                .build();

        LigneCmdEmployeDto ligneCmd = LigneCmdEmployeDto.builder()
                .id(1)
                .item(item)
                .prixUnitaire(BigDecimal.valueOf(100))
                .quantite(BigDecimal.valueOf(9))
                .build();

        EtatCommande etat = EtatCommande.EN_PREPARATION;


                List<LigneCmdEmployeDto> listLigneCmd = new ArrayList<>();
                listLigneCmd.add(ligneCmd);


        CommandeEmployeDto expectedCommande = CommandeEmployeDto.builder()

                .code("Cmd test 1")
                .dateCommande(Instant.now())
                .etatCommande(etat)
                .employe(employe)
                .ligneCmdEmployeDtos(listLigneCmd)
                .build();

        CommandeEmployeDto savedCommande = service.save(expectedCommande);

        Assertions.assertNotNull(savedCommande);
        Assertions.assertNotNull(savedCommande.getId());
        Assertions.assertEquals(expectedCommande.getCode(), savedCommande.getCode());



    }



}