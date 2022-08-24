package com.alc.gestock.services;

import com.alc.gestock.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto save(CategoryDto dto);


    CategoryDto findById(Integer id);


    CategoryDto findByCodeCategory(String codeCategory);

    List<CategoryDto> findAll();

    void delete(Integer id);
}
