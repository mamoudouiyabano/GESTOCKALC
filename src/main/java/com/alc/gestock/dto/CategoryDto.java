package com.alc.gestock.dto;


import com.alc.gestock.model.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;


@Data
@Getter
@Builder
public class CategoryDto {


    private Integer id;
    private String code;
    private String designation;


    @JsonIgnore
    private List<ItemDto> items;

    public static CategoryDto fromEntity(Category category)
    {

        if (category == null)
        {
            return null;
            // Exception
        }

        return CategoryDto.builder()
                .id(category.getId())
                .code(category.getCode())
                .designation(category.getDesignation())
                .build();

    }

    public static Category toEntity(CategoryDto categoryDto)
    {
        if (categoryDto == null)
        {
            return null;
        }
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setCode(categoryDto.getCode());
        category.setDesignation(categoryDto.getDesignation());
        return category;

    }





}
