package com.alc.gestock.services.impl;

import com.alc.gestock.dto.CategoryDto;
import com.alc.gestock.exception.EntityNotFoundException;
import com.alc.gestock.exception.ErrorCodes;
import com.alc.gestock.exception.InvalidEntityException;
import com.alc.gestock.services.CategoryService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService service;

    @Test
    public void shouldSaveCategoryWithSuccess() {

        CategoryDto expectedCategory = CategoryDto.builder()
                .code("Cat test test")
                .designation("Designation test")
                .build();

        CategoryDto savedCategory = service.save(expectedCategory);

        Assertions.assertNotNull(savedCategory);
        Assertions.assertNotNull(savedCategory.getId());
        Assertions.assertEquals(expectedCategory.getCode(), savedCategory.getCode());
        Assertions.assertEquals(expectedCategory.getDesignation(), savedCategory.getDesignation());


    }

    @Test
    public void shouldReadAllCategory() {
        List<CategoryDto> list = service.findAll();
        assertThat(list).size().isGreaterThan(0);
    }

    @Test
    public void shouldReadSingleCategoryById() {
        CategoryDto category = service.findById(37);
        Assertions.assertEquals("Designation test", category.getDesignation());
    }


    @Test
    public void shouldUpdateCategoryWithSuccess() {

        CategoryDto expectedCategory = CategoryDto.builder()
                .code("Cat test")
                .designation("Designation test")
                .build();

        CategoryDto savedCategory = service.save(expectedCategory);

        CategoryDto categoryToUpdate = savedCategory;
        categoryToUpdate.setCode("Cat update");

        savedCategory = service.save(categoryToUpdate);

        Assertions.assertNotNull(categoryToUpdate);
        Assertions.assertNotNull(categoryToUpdate.getId());
        Assertions.assertEquals(categoryToUpdate.getCode(), savedCategory.getCode());
        Assertions.assertEquals(categoryToUpdate.getDesignation(), savedCategory.getDesignation());


    }

    @Test
    public void shouldThrowInvalidEntityException() {

        CategoryDto expectedCategory = CategoryDto.builder().build();

        InvalidEntityException expectedException = assertThrows(InvalidEntityException.class, () -> service.save(expectedCategory));

        assertEquals(ErrorCodes.CATEGORY_NOT_VALID,expectedException.getErrorCodes());
        assertEquals(1,expectedException.getErrors().size());
        assertEquals("Veuillez renseigner le code de la categorie",expectedException.getErrors().get(0));


    }

//    @Test
//    public void shouldThrowEntityNotFoundException() {
//
//
//
//        EntityNotFoundException expectedException = assertThrows(EntityNotFoundException.class, () -> service.findById(0));
//
//        assertEquals(ErrorCodes.CATEGORY_NOT_FOUND,expectedException.getErrorCodes());
//        assertEquals("Aucune categorie avec l'ID = 0 n'ete trouve dans la BDD",expectedException.getMessage());
//
//
//    }


    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowEntityNotFoundException2() {
        service.findById(0);

    }

}