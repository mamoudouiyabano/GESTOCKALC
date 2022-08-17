package com.alc.gestock.repository;


import com.alc.gestock.model.Item;
import com.alc.gestock.model.MvtStk;
import com.alc.gestock.model.TypeMvtStk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface MvtStkRepository extends JpaRepository<MvtStk, Integer > {

    List<MvtStk> findMvtStksByTypeMvt(TypeMvtStk type);
    List<MvtStk> findMvtStksByItem(Item item);

    @Query("select sum(m.quantite) from MvtStk m where m.item.id =  :idItem ")
    BigDecimal stockReelItem(@Param("idItem") Integer idItem);

    List<MvtStk> findAllByItemId(Integer idItem);
}
