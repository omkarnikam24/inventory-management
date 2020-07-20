package com.example.inventory.item.service;

import com.example.inventory.item.dto.ItemDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author omkar
 * @apiNote An Item Service
 */
public interface ItemService {

    Optional<ItemDTO> saveItem(ItemDTO itemDTO);

    Optional<ItemDTO> getItemById(Long productCode);

    List<ItemDTO> getAllItems();

    void validateAndUpdateItems(Set<ItemDTO> itemDTOS);
}
