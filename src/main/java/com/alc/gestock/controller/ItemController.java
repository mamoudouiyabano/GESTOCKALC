package com.alc.gestock.controller;

import com.alc.gestock.controller.api.ItemApi;
import com.alc.gestock.dto.ItemDto;
import com.alc.gestock.dto.LigneCmdEmployeDto;
import com.alc.gestock.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ItemController implements ItemApi {


    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @Override
    public ItemDto save(ItemDto dto) {
        return itemService.save(dto);
    }

    @Override
    public ItemDto findById(Integer id) {
        return itemService.findById(id);
    }

    @Override
    public ItemDto findByCodeitem(String codeItem) {
        return itemService.findByCodeItem(codeItem);
    }

    @Override
    public List<ItemDto> findAll() {
        return itemService.findAll();
    }


    @Override
    public List<LigneCmdEmployeDto> findHistoriqueCommandeEmploye(Integer idItem) {
        return itemService.findHistoriqueCommandeEmploye(idItem);
    }


    @Override
    public List<ItemDto> findAllItemByIdCategory(Integer idCategory) {
        return itemService.findAllItemByIdCategory(idCategory);
    }

    @Override
    public void delete(Integer id) {
        itemService.delete(id);

    }
}
