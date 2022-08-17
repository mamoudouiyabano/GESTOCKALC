package com.alc.gestock.repository;

import com.alc.gestock.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    Optional<Item> findItemByCodeItem(String codeItem);

    List<Item> findAllByCategoryId(Integer idCategory);
}
