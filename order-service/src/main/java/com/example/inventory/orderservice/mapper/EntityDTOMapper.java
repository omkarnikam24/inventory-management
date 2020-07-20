package com.example.inventory.orderservice.mapper;

import com.example.inventory.orderservice.dto.ItemDTO;
import com.example.inventory.orderservice.dto.OrderDTO;
import com.example.inventory.orderservice.entity.ItemIds;
import com.example.inventory.orderservice.entity.Order;

import java.util.stream.Collectors;

/**
 * @author omkar
 * @apiNote This Mapper converts DTOs to Entities and vice versa.
 */
public class EntityDTOMapper {

    public static Order convertOrderDtoToEntity(OrderDTO dto) {

        Order order = new Order();
        order.setCustomerName(dto.getCustomerName());
        order.setOrderDate(dto.getOrderDate());
        order.setShippingAddress(dto.getShippingAddress());
        order.setItemIds(dto.getItems().stream().map(i -> new ItemIds(i.getProductCode(), i.getQuantity())).collect(Collectors.toSet()));
        return order;
    }

    public static OrderDTO convertOrderEntityToDto(Order order) {
        if (null == order)
            return null;
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(order.getOrderId());
        dto.setCustomerName(order.getCustomerName());
        dto.setOrderDate(order.getOrderDate());
        dto.setShippingAddress(order.getShippingAddress());
        dto.setItems(order.getItemIds().stream().map(i -> new ItemDTO(i.getProductCode(), i.getQuantity())).collect(Collectors.toSet()));
        return dto;
    }
}