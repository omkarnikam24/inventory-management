package com.example.inventory.orderservice.controller;

import com.example.inventory.orderservice.dto.OrderDTO;
import com.example.inventory.orderservice.exception.InvalidRequestException;
import com.example.inventory.orderservice.exception.OrderCreationException;
import com.example.inventory.orderservice.exception.OrderNotFoundException;
import com.example.inventory.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * @author omkar
 * @apiNote A Rest Controller for handling all order related endpoints.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * @param orderDTO
     * @param errors
     * @return ResponseEntity
     * @throws InvalidRequestException
     * @throws OrderCreationException
     */
    @Operation(summary = "Create an order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class))}),
            @ApiResponse(responseCode = "422", description = "Invalid Request",
                    content = @Content),
            @ApiResponse(responseCode = "417", description = "Failed to create an Order",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderDTO orderDTO, Errors errors) {
        log.info("Creating an Order");
        if (errors.hasErrors()) {
            String reqErrorDtls = errors.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining(","));
            log.info("Invalid request");
            log.error("Invalid request, errors - {}", reqErrorDtls);
            throw new InvalidRequestException(reqErrorDtls);
        }
        try {
            Optional<OrderDTO> newOrder = orderService.saveOrder(orderDTO);
            log.info("Order created with order id - {}", newOrder.get().getOrderId());
            return new ResponseEntity<>(newOrder.get(), HttpStatus.CREATED);
        } catch (OrderCreationException o) {
            log.info("Could not create an order");
            log.error("Could not create an order, cause - {}", o.getMessage());
            throw new OrderCreationException(o.getMessage());
        }
    }

    /**
     * @param orderId
     * @return ResponseEntity - which includes the requested order and an HTTP Status Code
     * @throws OrderNotFoundException
     */
    @Operation(summary = "Get an Order by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = @Content)
    })
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId) {
        log.info("Getting order details for order id - {}", orderId);
        Optional<OrderDTO> order = orderService.getOrderById(orderId);
        if (order.isPresent()) {
            log.info("Order Details - {}", order.get());
            return new ResponseEntity<>(order.get(), HttpStatus.OK);
        }
        log.info("Order not found for order id - {}", orderId);
        throw new OrderNotFoundException("Order not found for order id " + orderId);
    }

    /**
     * @return ResponseEntity - which includes list of orders and an HTTP Status Code
     * @throws OrderNotFoundException
     */
    @Operation(summary = "Get all Orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Got all orders",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = List.class))}),
            @ApiResponse(responseCode = "404", description = "No Orders found",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getOrders() {
        log.info("Getting all orders");
        List<OrderDTO> result = orderService.getAllOrders();
        if (!result.isEmpty()) {
            log.info("Successfully retrieved {} orders", result.size());
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        log.info("No orders found");
        throw new OrderNotFoundException("No orders found");
    }
}