package com.alc.gestock.repository;

import com.alc.gestock.model.LigneCmdEmploye;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandeEmployeRepository extends JpaRepository<LigneCmdEmploye, Integer> {

    List<LigneCmdEmploye> findAllByCommandeEmployeId(Integer idCommande);
    List<LigneCmdEmploye> findAllByItemId(Integer idItem);


}
