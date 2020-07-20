package com.example.inventory.orderservice.service;

import com.example.inventory.orderservice.dto.OrderDTO;
import com.example.inventory.orderservice.entity.Order;
import com.example.inventory.orderservice.exception.OrderCreationException;
import com.example.inventory.orderservice.feign.ItemServiceProxy;
import com.example.inventory.orderservice.mapper.EntityDTOMapper;
import com.example.inventory.orderservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author omkar
 * @apiNote Implementation of Order Service
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ItemServiceProxy proxy;

    /**
     * @param orderDTO
     * @return Optional of Order DTO
     * @throws OrderCreationException
     */
    @Override
    public Optional<OrderDTO> saveOrder(OrderDTO orderDTO) {
        log.info("Saving Order");
        Optional<OrderDTO> order = Optional.empty();
        log.info("Requesting Item service to validate and update quantities");

        try {
            ResponseEntity<String> response = proxy.updateAndRetrieveItems(orderDTO.getItems());
            log.info("Quantities updated successfully, creating an order now");
            return order = Optional.of(EntityDTOMapper.convertOrderEntityToDto(orderRepo.save(EntityDTOMapper.convertOrderDtoToEntity(orderDTO))));
        } catch (RuntimeException exception) {
            log.info("Error while updating item quantities - {}", exception.getMessage());
            throw new OrderCreationException(exception.getMessage());
        }
    }

    /**
     * @param id - Order Id
     * @return Optional of Order DTO
     */
    @Override
    public Optional<OrderDTO> getOrderById(Long id) {
        Optional<OrderDTO> orderDTO = Optional.empty();
        Optional<Order> order = orderRepo.findById(id);
        if (order.isPresent())
            orderDTO = Optional.of(EntityDTOMapper.convertOrderEntityToDto(order.get()));
        return orderDTO;
    }

    /**
     * @return List of Order DTOs
     */
    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepo.findAll().stream().map(order -> EntityDTOMapper.convertOrderEntityToDto(order)).collect(Collectors.toList());
    }
}
