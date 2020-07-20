package com.example.inventory.item.service;

import com.example.inventory.item.dto.ItemDTO;
import com.example.inventory.item.entity.Item;
import com.example.inventory.item.mapper.EntityDTOMapper;
import com.example.inventory.item.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author omkar
 * @apiNote Implementation of Item Service
 */
@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    private ItemRepository itemRepo;

    /**
     * @param itemDTO
     * @return Optional of Item DTO
     */
    @Override
    public Optional<ItemDTO> saveItem(ItemDTO itemDTO) {
        Optional<ItemDTO> item = Optional.of(EntityDTOMapper.convertItemEntityToDto(itemRepo.save(EntityDTOMapper.convertItemDtoToEntity(itemDTO))));
        return item;
    }

    /**
     * @param productCode
     * @return Optional of Order DTO
     */
    @Override
    public Optional<ItemDTO> getItemById(Long productCode) {
        Optional<ItemDTO> itemDTO = Optional.empty();
        Optional<Item> item = itemRepo.findById(productCode);
        if (item.isPresent()) {
            itemDTO = Optional.of(EntityDTOMapper.convertItemEntityToDto(item.get()));
        }
        return itemDTO;
    }

    /**
     * @return List of Item DTOs
     */
    @Override
    public List<ItemDTO> getAllItems() {
        return itemRepo.findAll().stream().map(item -> EntityDTOMapper.convertItemEntityToDto(item)).collect(Collectors.toList());
    }

    /**
     * Validates if items for corresponding product codes are present or not.
     * If present check if there is sufficient quantity present in the database for a particular item.
     * If true, updates the quantities for requested items.
     * Else throws an Exception
     *
     * @param itemDTOS
     * @throws RuntimeException
     */
    @Override
    @Transactional
    public void validateAndUpdateItems(Set<ItemDTO> itemDTOS) {
        log.info("Validating if item quantities are sufficient");
        List<Item> items = itemRepo.findAllById(itemDTOS.stream().map(item -> item.getProductCode()).collect(Collectors.toSet()));
        if (items.isEmpty()) {
            log.info("Items not found");
            throw new RuntimeException("Items not found");
        }
        Map<Long, ItemDTO> dtoMap = itemDTOS.stream().collect(Collectors.toMap(i -> i.getProductCode(), itemDTO -> itemDTO));

        items.forEach(item -> {
            int quantity = dtoMap.get(item.getProductCode()).getQuantity();
            if (item.getQuantity() < quantity) {
                log.info("Invalid Quantity for item : {} - {}", item.getProductCode(), item.getProductName());
                throw new RuntimeException("Invalid Quantity for item : " + item.getProductCode() + " - " + item.getProductName());
            }
            item.setQuantity(item.getQuantity() - quantity);
        });
        log.info("Successfully validated and updated item quantities");
    }
}