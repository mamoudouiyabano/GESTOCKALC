package com.alc.gestock.controller;


import com.alc.gestock.controller.api.EmployeApi;
import com.alc.gestock.dto.EmployeDto;
import com.alc.gestock.services.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class EmployeController implements EmployeApi {

    private com.alc.gestock.services.EmployeService employeService;

    @Autowired
    public EmployeController(EmployeService employeService) {
        this.employeService = employeService;
    }

    @Override
    public EmployeDto save(EmployeDto dto) {
        return employeService.save(dto);
    }

    @Override
    public EmployeDto findById(Integer id) {
        return employeService.findById(id);
    }

    @Override
    public EmployeDto findByNomEmploye(String nomEmploye) {
        return employeService.findByNomEmploye(nomEmploye);
    }

    @Override
    public List<EmployeDto> findAll() {
        return employeService.findAll();
    }

    @Override
    public void delete(Integer id) {
        employeService.delete(id);
    }
}
