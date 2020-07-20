package com.example.inventory.item.mapper;

import com.example.inventory.item.dto.ItemDTO;
import com.example.inventory.item.entity.Item;

/**
 * @author omkar
 * @apiNote This Mapper converts DTOs to Entities and vice versa.
 */
public class EntityDTOMapper {

    public static Item convertItemDtoToEntity(ItemDTO dto) {
        Item item = new Item();
        item.setProductCode(dto.getProductCode());
        item.setProductName(dto.getProductName());
        item.setQuantity(dto.getQuantity());
        return item;
    }

    public static ItemDTO convertItemEntityToDto(Item item) {
        if (null == item)
            return null;
        ItemDTO dto = new ItemDTO();
        dto.setProductCode(item.getProductCode());
        dto.setProductName(item.getProductName());
        dto.setQuantity(item.getQuantity());
        return dto;
    }
}
