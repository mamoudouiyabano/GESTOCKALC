package com.alc.gestock.repository;


import com.alc.gestock.model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeRepository extends JpaRepository<Employe, Integer> {

    Optional<Employe> findEmployeByNom(String nomEmploye);
}
