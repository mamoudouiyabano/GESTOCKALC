package com.alc.gestock.services.impl;

import com.alc.gestock.dto.CategoryDto;
import com.alc.gestock.dto.ItemDto;
import com.alc.gestock.exception.EntityNotFoundException;
import com.alc.gestock.exception.ErrorCodes;
import com.alc.gestock.exception.InvalidEntityException;

import com.alc.gestock.services.ItemService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class ItemServiceimplTest {

    @Autowired
    private ItemService service;

    @Test
    public void shouldSaveItemWithSuccess() {

        CategoryDto ItemCategory = CategoryDto.builder()
                .id(14)
                .build();

        ItemDto expectedItem = ItemDto.builder()
                .codeItem("Item test test")
                .designation("Designation test")
                .prixUnitaireHt(BigDecimal.valueOf(100))
                .prixUnitaireTtc(BigDecimal.valueOf(110))
                .category(ItemCategory)
                .build();

        ItemDto savedItem = service.save(expectedItem);

        Assertions.assertNotNull(savedItem);
        Assertions.assertNotNull(savedItem.getId());
        Assertions.assertEquals(expectedItem.getCodeItem(), savedItem.getCodeItem());
        Assertions.assertEquals(expectedItem.getDesignation(), savedItem.getDesignation());
        Assertions.assertEquals(expectedItem.getCategory(), savedItem.getCategory());


    }

    @Test
    public void shouldReadAllItems() {
        List<ItemDto> list = service.findAll();
        assertThat(list).size().isGreaterThan(0);
    }

    @Test
    public void shouldReadSingleItemById() {
        ItemDto items = service.findById(18);
        Assertions.assertEquals("Item de test 2", items.getDesignation());
    }

    @Test
    public void shouldReadSingleItemByCode() {
        ItemDto items = service.findByCodeItem("Item test 2");
        Assertions.assertEquals("Item de test 2", items.getDesignation());
    }

    @Test
    public void shouldReadAllItemByCateroyId() {
        List<ItemDto> list = service.findAllItemByIdCategory(10);
        assertThat(list).size().isGreaterThan(0);
    }





    @Test(expected = InvalidEntityException.class)
    public void shouldThrowInvalidEntityException() {

        ItemDto expectedItem = ItemDto.builder().build();
        ItemDto savedItem = service.save(expectedItem);
    }


    @Test
    public void shouldThrowEntityNotFoundException() {

        EntityNotFoundException expectedException = assertThrows(EntityNotFoundException.class, () -> service.findById(1000));
        assertEquals(ErrorCodes.ITEM_NOT_FOUND,expectedException.getErrorCodes());

    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowEntityNotFoundException2() {
        service.findById(0);
    }

}