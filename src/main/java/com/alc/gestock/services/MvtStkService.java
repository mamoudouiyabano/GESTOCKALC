package com.alc.gestock.services;

import com.alc.gestock.dto.MvtStkDto;
import com.alc.gestock.model.Item;
import com.alc.gestock.model.TypeMvtStk;

import java.math.BigDecimal;
import java.util.List;

public interface MvtStkService {

    MvtStkDto save(MvtStkDto dto);

    List<MvtStkDto> findByTypeMvt(TypeMvtStk typeMvtStk);

    List<MvtStkDto> findByItem(Item item);

    List<MvtStkDto> findAll();

    BigDecimal stockReelItem(Integer idItem);

    List<MvtStkDto> mvtStkItem(Integer idItem);

    MvtStkDto entreeStock(MvtStkDto dto);

    MvtStkDto sortieStock(MvtStkDto dto);

    MvtStkDto correctionStockPos(MvtStkDto dto);

    MvtStkDto correctionStockNeg(MvtStkDto dto);


    void delete(Integer id);
}
