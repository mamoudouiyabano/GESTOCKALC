package com.alc.gestock.repository;

import com.alc.gestock.model.CommandeEmploye;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommandeEmployeRepository extends JpaRepository<CommandeEmploye, Integer> {

    Optional<CommandeEmploye> findCommandeEmployeByCode(String code);
    List<CommandeEmploye> findAllByEmployeId(Integer id);

}
