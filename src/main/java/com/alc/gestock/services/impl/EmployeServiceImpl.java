package com.alc.gestock.services.impl;

import com.alc.gestock.dto.EmployeDto;
import com.alc.gestock.exception.EntityNotFoundException;
import com.alc.gestock.exception.ErrorCodes;
import com.alc.gestock.exception.InvalidEntityException;
import com.alc.gestock.exception.InvalidOperationException;
import com.alc.gestock.model.CommandeEmploye;
import com.alc.gestock.model.Employe;
import com.alc.gestock.repository.CommandeEmployeRepository;
import com.alc.gestock.repository.EmployeRepository;
import com.alc.gestock.services.EmployeService;
import com.alc.gestock.validator.EmployeValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeServiceImpl implements EmployeService {

    private EmployeRepository employeRepository;
    private CommandeEmployeRepository commandeEmployeRepository;

    @Autowired
    public EmployeServiceImpl(EmployeRepository employeRepository, CommandeEmployeRepository commandeEmployeRepository) {
        this.employeRepository = employeRepository;
        this.commandeEmployeRepository = commandeEmployeRepository;
    }

    @Override
    public EmployeDto save(EmployeDto dto) {
        List<String> errors = EmployeValidator.Validate(dto);

        if (!errors.isEmpty())
        {
            log.error("Employe is not valid");
            throw new InvalidEntityException("Employe non valide" , ErrorCodes.EMPLOYE_NOT_VALID, errors );
        }
        return EmployeDto.fromEntity(employeRepository.save(EmployeDto.toEntity(dto)));
    }

    @Override
    public EmployeDto findById(Integer id) {
        if (id == null)
        {
            log.error("L'id du Employe est null");
            return null;
        }
        Optional<Employe> Employe = employeRepository.findById(id);
        EmployeDto dto = EmployeDto.fromEntity(Employe.get());

        return Optional.of(dto).orElseThrow(()->new EntityNotFoundException("Employe not fond",ErrorCodes.EMPLOYE_NOT_FOUND));
    }

    @Override
    public EmployeDto findByNomEmploye(String nomEmploye) {
        if (!StringUtils.hasLength(nomEmploye))
        {
            log.error("Le nom du Employe est null");
            return null;
        }
        Optional<Employe> Employe = employeRepository.findEmployeByNom(nomEmploye);
        EmployeDto dto = EmployeDto.fromEntity(Employe.get());

        return Optional.of(dto).orElseThrow(()->new EntityNotFoundException("Employe not found",ErrorCodes.EMPLOYE_NOT_FOUND));

    }

    @Override
    public List<EmployeDto> findAll() {
        return employeRepository.findAll().stream()
                .map(EmployeDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null)
        {
            log.error("L'id du Employe est null");
        }

        List<CommandeEmploye> commandeEmployes = commandeEmployeRepository.findAllByEmployeId(id);
        if(!commandeEmployes.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer un Employe qui a des cmds Employes", ErrorCodes.EMPLOYE_ALREADY_IN_USE);
        }

        employeRepository.deleteById(id);
    }
}
