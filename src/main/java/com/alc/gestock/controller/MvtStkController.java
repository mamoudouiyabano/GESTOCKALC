package com.alc.gestock.controller;

import com.alc.gestock.controller.api.MvtStkApi;
import com.alc.gestock.dto.MvtStkDto;
import com.alc.gestock.model.Item;
import com.alc.gestock.model.TypeMvtStk;
import com.alc.gestock.services.MvtStkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin("*")
public class MvtStkController implements MvtStkApi {

    private MvtStkService mvtStkService;

    @Autowired
    public MvtStkController(MvtStkService mvtStkService) {
        this.mvtStkService = mvtStkService;
    }

    @Override
    public MvtStkDto save(MvtStkDto dto) {
        return mvtStkService.save(dto);
    }

    @Override
    public List<MvtStkDto> findByType(TypeMvtStk typeMvtStk) {
        return mvtStkService.findByTypeMvt(typeMvtStk);
    }

    @Override
    public List<MvtStkDto> findByItem(Integer id) {
        Item item = new Item();
        item.setId(id);
        return mvtStkService.findByItem(item);
    }

    @Override
    public List<MvtStkDto> findAll() {
        return mvtStkService.findAll();
    }

    @Override
    public void delete(Integer id) {
        mvtStkService.delete(id);
    }

    @Override
    public BigDecimal stockReelItem(Integer idItem) {
        return mvtStkService.stockReelItem(idItem);
    }

    @Override
    public List<MvtStkDto> mvtStkItem(Integer idItem) {
        return mvtStkService.mvtStkItem(idItem);
    }

    @Override
    public MvtStkDto entreeStock(MvtStkDto dto) {
        return mvtStkService.entreeStock(dto);
    }

    @Override
    public MvtStkDto sortieStock(MvtStkDto dto) {
        return mvtStkService.sortieStock(dto);
    }

    @Override
    public MvtStkDto correctionStockPos(MvtStkDto dto) {
        return mvtStkService.correctionStockPos(dto);
    }

    @Override
    public MvtStkDto correctionStockNeg(MvtStkDto dto) {
        return mvtStkService.correctionStockNeg(dto);
    }
}
