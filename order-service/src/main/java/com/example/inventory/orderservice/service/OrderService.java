package com.example.inventory.orderservice.service;

import com.example.inventory.orderservice.dto.OrderDTO;

import java.util.List;
import java.util.Optional;

/**
 * @author omkar
 * @apiNote An Order Service
 */
public interface OrderService {

    Optional<OrderDTO> saveOrder(OrderDTO orderDTO);

    Optional<OrderDTO> getOrderById(Long id);

    List<OrderDTO> getAllOrders();
}
