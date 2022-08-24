package com.alc.gestock.services.impl;


import com.alc.gestock.dto.CategoryDto;
import com.alc.gestock.exception.EntityNotFoundException;
import com.alc.gestock.exception.ErrorCodes;
import com.alc.gestock.exception.InvalidEntityException;
import com.alc.gestock.exception.InvalidOperationException;
import com.alc.gestock.model.Category;
import com.alc.gestock.model.Item;
import com.alc.gestock.repository.CategoryRepository;
import com.alc.gestock.repository.ItemRepository;
import com.alc.gestock.services.CategoryService;
import com.alc.gestock.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ItemRepository itemRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ItemRepository itemRepository) {
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public CategoryDto save(CategoryDto dto) {

        List<String> errors = CategoryValidator.validate(dto);


        if (!errors.isEmpty())
        {

            log.error("Category is not valid");
            throw new InvalidEntityException("categorie n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID , errors);
        }

        return CategoryDto.fromEntity(
                categoryRepository.save(
                        CategoryDto.toEntity(dto)
                )
        );


    }

//    @Override
//    public CategoryDto findById(Integer id) {
//
//        if (id == null)
//        {
//            log.error("Category ID is null");
//            return null;
//        }
//        Optional<Category> category = categoryRepository.findById(id);
//        CategoryDto dto = CategoryDto.fromEntity(category.get());
//
//        return Optional.of(dto).orElseThrow(()->
//                new EntityNotFoundException("Category not found" ,
//                        ErrorCodes.CATEGORY_NOT_FOUND)
//        );
//    }

    @Override
    public CategoryDto findById(Integer id) {

        if (id == null)
        {
            log.error("Category ID is null");
            return null;
        }

        return categoryRepository.findById(id)
                .map(CategoryDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune categorie avec l'ID = " + id + " n'ete trouve dans la BDD",
                        ErrorCodes.CATEGORY_NOT_FOUND
                ));



    }

    @Override
    public CategoryDto findByCodeCategory(String codeCategory) {
        if (!StringUtils.hasLength(codeCategory))
        {
            log.error("Category code is not found");
            return null;
        }
        Optional<Category> category = categoryRepository.findCategoryByCode(codeCategory);
        CategoryDto dto = CategoryDto.fromEntity(category.get());

        return Optional.of(dto).orElseThrow(()->
                new EntityNotFoundException("Category not found" ,
                        ErrorCodes.CATEGORY_NOT_FOUND)
        );
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null)
        {
            log.error("Category ID is null");
        }

        List<Item> items = itemRepository.findAllByCategoryId(id);
        if(!items.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer une category qui est deja liee a un item", ErrorCodes.CATEGORY_ALREADY_IN_USE);
        }


        categoryRepository.deleteById(id);

    }
}
