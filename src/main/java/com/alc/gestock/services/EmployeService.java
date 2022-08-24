package com.alc.gestock.services;


import com.alc.gestock.dto.EmployeDto;

import java.util.List;

public interface EmployeService {

    EmployeDto save(EmployeDto dto);

    EmployeDto findById(Integer id);

    EmployeDto findByNomEmploye(String nomEmploye);

    List<EmployeDto> findAll();

    void delete(Integer id);
}
