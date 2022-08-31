package com.alc.gestock.services.impl;

import com.alc.gestock.dto.MvtStkDto;
import com.alc.gestock.exception.ErrorCodes;
import com.alc.gestock.exception.InvalidEntityException;
import com.alc.gestock.model.Item;
import com.alc.gestock.model.MvtStk;
import com.alc.gestock.model.TypeMvtStk;
import com.alc.gestock.repository.ItemRepository;
import com.alc.gestock.repository.MvtStkRepository;
import com.alc.gestock.services.ItemService;
import com.alc.gestock.services.MvtStkService;
import com.alc.gestock.validator.MvtStockValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MvtStkServiceImpl implements MvtStkService {

    private MvtStkRepository mvtStkRepository;
    private com.alc.gestock.repository.ItemRepository ItemRepository;
    private com.alc.gestock.services.ItemService ItemService;

    @Autowired
    public MvtStkServiceImpl(MvtStkRepository mvtStkRepository, ItemRepository ItemRepository) {
        this.mvtStkRepository = mvtStkRepository;
        this.ItemRepository = ItemRepository;
    }

    @Override
    public MvtStkDto save(MvtStkDto dto) {
        List<String> errors = MvtStockValidator .Validate(dto);

        if (!errors.isEmpty())
        {
            log.error("Movement not valid");
            throw new InvalidEntityException("l'objet mouvement stock n'est pas valide", ErrorCodes.MVT_STK_NOT_VALID, errors);
        }


        return MvtStkDto.fromEntity(mvtStkRepository.save(MvtStkDto.toEntity(dto)));
    }

    @Override
    public List<MvtStkDto> findByTypeMvt(TypeMvtStk typeMvtStk) {
        if (typeMvtStk == null)
        {
            log.error("le type du mvt est null");
            return null;
        }

        List<MvtStk> mvtStks = mvtStkRepository.findMvtStksByTypeMvt(typeMvtStk);
        return mvtStks.stream()
                .map(MvtStkDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<MvtStkDto> findByItem(Item Item) {
        if (Item == null)
        {
            log.error("l'Item  est null");
            return null;
        }

        List<MvtStk> mvtStks = mvtStkRepository.findMvtStksByItem(Item);
        return mvtStks.stream()
                .map(MvtStkDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<MvtStkDto> findAll() {
        return mvtStkRepository.findAll()
                .stream().map(MvtStkDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal stockReelItem(Integer idItem) {
        if(idItem == null) {
            log.warn("ID Item is NULL");
            return   BigDecimal.valueOf(-1);
        }
//        ItemService.findById(idItem);
        return mvtStkRepository.stockReelItem(idItem);
    }

    @Override
    public List<MvtStkDto> mvtStkItem(Integer idItem) {
        return mvtStkRepository.findAllByItemId(idItem).stream()
                .map(MvtStkDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MvtStkDto entreeStock(MvtStkDto dto) {
        List<String> errors  = MvtStockValidator.Validate(dto);
        if (!errors.isEmpty())
        {
            log.error("Movement not valid");
            throw new InvalidEntityException("l'objet mouvement stock n'est pas valide", ErrorCodes.MVT_STK_NOT_VALID, errors);
        }

        dto.setQuantite(
                BigDecimal.valueOf(
                        Math.abs(dto.getQuantite().doubleValue())
                )
        );

        dto.setTypeMvt(TypeMvtStk.ENTREE);

        return MvtStkDto.fromEntity(
                mvtStkRepository.save(MvtStkDto.toEntity(dto))
        );


    }

    @Override
    public MvtStkDto sortieStock(MvtStkDto dto) {
        List<String> errors  = MvtStockValidator.Validate(dto);
        if (!errors.isEmpty())
        {
            log.error("Movement not valid");
            throw new InvalidEntityException("l'objet mouvement stock n'est pas valide", ErrorCodes.MVT_STK_NOT_VALID, errors);
        }

        dto.setQuantite(
                BigDecimal.valueOf(
                        Math.abs(dto.getQuantite().doubleValue())*-1
                )
        );

        dto.setTypeMvt(TypeMvtStk.SORTIE);

        return MvtStkDto.fromEntity(
                mvtStkRepository.save(MvtStkDto.toEntity(dto))
        );
    }

    @Override
    public MvtStkDto correctionStockPos(MvtStkDto dto) {
        List<String> errors  = MvtStockValidator.Validate(dto);
        if (!errors.isEmpty())
        {
            log.error("Movement not valid");
            throw new InvalidEntityException("l'objet mouvement stock n'est pas valide", ErrorCodes.MVT_STK_NOT_VALID, errors);
        }

        dto.setQuantite(
                BigDecimal.valueOf(
                        Math.abs(dto.getQuantite().doubleValue())
                )
        );

        dto.setTypeMvt(TypeMvtStk.CORRECTION_POS);

        return MvtStkDto.fromEntity(
                mvtStkRepository.save(MvtStkDto.toEntity(dto))
        );
    }

    @Override
    public MvtStkDto correctionStockNeg(MvtStkDto dto) {
        List<String> errors  = MvtStockValidator.Validate(dto);
        if (!errors.isEmpty())
        {
            log.error("Movement not valid");
            throw new InvalidEntityException("l'objet mouvement stock n'est pas valide", ErrorCodes.MVT_STK_NOT_VALID, errors);
        }

        dto.setQuantite(
                BigDecimal.valueOf(
                        Math.abs(dto.getQuantite().doubleValue()) * -1
                )
        );

        dto.setTypeMvt(TypeMvtStk.CORRECTION_NEG);

        return MvtStkDto.fromEntity(
                mvtStkRepository.save(MvtStkDto.toEntity(dto))
        );
    }

    @Override
    public void delete(Integer id) {
        if (id == null)
        {
            log.error("id IS NULL");
        }

        mvtStkRepository.deleteById(id);

    }
}
