package com.alc.gestock.services.impl;

import com.alc.gestock.dto.CategoryDto;
import com.alc.gestock.dto.ItemDto;
import com.alc.gestock.dto.LigneCmdEmployeDto;
import com.alc.gestock.dto.MvtStkDto;
import com.alc.gestock.exception.EntityNotFoundException;
import com.alc.gestock.exception.ErrorCodes;
import com.alc.gestock.exception.InvalidEntityException;
import com.alc.gestock.exception.InvalidOperationException;
import com.alc.gestock.model.Item;
import com.alc.gestock.model.LigneCmdEmploye;
import com.alc.gestock.model.SourceMvtStk;
import com.alc.gestock.model.TypeMvtStk;
import com.alc.gestock.repository.ItemRepository;
import com.alc.gestock.repository.LigneCommandeEmployeRepository;
import com.alc.gestock.services.ItemService;
import com.alc.gestock.services.MvtStkService;
import com.alc.gestock.validator.ItemValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ItemServiceimpl implements ItemService {

    private ItemRepository itemRepository;
    private LigneCommandeEmployeRepository cmdEmployeRepository;
    private MvtStkService mvtStkService;

    @Autowired
    public ItemServiceimpl(
            ItemRepository itemRepository, LigneCommandeEmployeRepository cmdEmployeRepository,MvtStkService mvtStkService) {
        this.itemRepository = itemRepository;
        this.cmdEmployeRepository = cmdEmployeRepository;
        this.mvtStkService = mvtStkService;
    }

    @Override
    public ItemDto save(ItemDto dto) {
        List<String> errors = ItemValidator.validate(dto);
        if(!errors.isEmpty()) {
            log.error("Item is not valid {}", dto);
            throw new InvalidEntityException("L'Item n'est pas valide", ErrorCodes.ITEM_NOT_FOUND, errors);
        }

        Optional<Item> item = itemRepository.findItemByCodeItem(dto.getCodeItem());
        ItemDto itemDto = ItemDto.fromEntity(item.get());

        ItemDto  savedItem2 = ItemDto.fromEntity(ItemDto.toEntity(dto));
        savedItem2.setId(itemDto.getId());

        if (itemDto != null)
        {

            dto.setQuantite(
                    BigDecimal.valueOf(
                            Math.abs(dto.getQuantite().doubleValue() + itemDto.getQuantite().doubleValue())
                    )
            );
            dto.setId(itemDto.getId());
        }

        ItemDto savedItem  = ItemDto.fromEntity(itemRepository.save(ItemDto.toEntity(dto)));
        updateMvtStk(savedItem2);
        return savedItem;
    }

    @Override
    public ItemDto findById(Integer id) {
        if(id == null) {
            log.error("Item ID is null");
            return  null;
        }

//        Optional<Item> item = itemRepository.findById(id);
//
//        ItemDto dto = ItemDto.fromEntity(item.get());
//
//        return Optional.of(dto).orElseThrow(()-> new EntityNotFoundException(
//                "Aucun Item avec l'ID = " + id + " na  pâs été trouvé dans la BDD",
//                ErrorCodes.ITEM_NOT_FOUND
//        ));

        return itemRepository.findById(id)
                .map(ItemDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun Item avec l'ID = " + id + " na  pâs été trouvé dans la BDD",
                        ErrorCodes.ITEM_NOT_FOUND
                ));

    }

    @Override
    public ItemDto findByCodeItem(String codeItem) {
        if(!StringUtils.hasLength(codeItem)) {
            log.error("Item CODE is null");
            return  null;
        }

        Optional<Item> Item = itemRepository.findItemByCodeItem(codeItem);

        ItemDto dto = ItemDto.fromEntity(Item.get());

        return Optional.of(dto).orElseThrow(()-> new EntityNotFoundException(
                "Aucun Item avec le code = " +codeItem+ " na  pâs été trouvé dans la BDD",
                ErrorCodes.ITEM_NOT_FOUND
        ));


    }

    @Override
    public List<ItemDto> findAll() {
        return itemRepository.findAll().stream()
                .map(ItemDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<LigneCmdEmployeDto> findHistoriqueCommandeEmploye(Integer idItem) {
        return cmdEmployeRepository.findAllByItemId(idItem).stream()
                .map(LigneCmdEmployeDto::fromEntity)
                .collect(Collectors.toList());
    }


    @Override
    public List<ItemDto> findAllItemByIdCategory(Integer idCategory) {
        return itemRepository.findAllByCategoryId(idCategory).stream()
                .map(ItemDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if(id == null) {
            log.error("Item ID is null");
            return;
        }

        List<LigneCmdEmploye> ligneCmdEmployes = cmdEmployeRepository.findAllByItemId(id);
        if(!ligneCmdEmployes.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer un Item deja utilise dans cmds Employes", ErrorCodes.ITEM_ALREADY_IN_USE);
        }

        itemRepository.deleteById(id);

    }


    private void updateMvtStk(ItemDto itemDto) {


            MvtStkDto mvtStkDto = MvtStkDto.builder()
                    .item(itemDto)
                    .dateMvt(Instant.now())
                    .typeMvt(TypeMvtStk.ENTREE)
                    .sourceMvtStk(SourceMvtStk.ENTREE_EN_STOCK)
                    .quantite(itemDto.getQuantite())

                    .build();
            mvtStkService.entreeStock(mvtStkDto);

    }

}
