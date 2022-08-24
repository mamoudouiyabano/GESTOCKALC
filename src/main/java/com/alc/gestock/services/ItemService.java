package com.alc.gestock.services;

import com.alc.gestock.dto.ItemDto;
import com.alc.gestock.dto.LigneCmdEmployeDto;

import java.util.List;

public interface ItemService {

    ItemDto save(ItemDto dto);

    ItemDto findById(Integer id);

    ItemDto findByCodeItem(String codeItem);


    List<ItemDto> findAll();

    List<LigneCmdEmployeDto> findHistoriqueCommandeEmploye(Integer idItem);

    List<ItemDto> findAllItemByIdCategory(Integer idCategory);

    void delete(Integer id);

}
