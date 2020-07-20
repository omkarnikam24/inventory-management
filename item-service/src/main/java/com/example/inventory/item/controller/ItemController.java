package com.example.inventory.item.controller;

import com.example.inventory.item.dto.ItemDTO;
import com.example.inventory.item.exception.InvalidRequestException;
import com.example.inventory.item.exception.ItemCreationException;
import com.example.inventory.item.exception.ItemNotFoundException;
import com.example.inventory.item.service.ItemService;
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
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author omkar
 * @apiNote A Rest Controller for handling all item related endpoints.
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    private static final Logger log = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    /**
     * @param itemDTO
     * @param errors
     * @return ResponseEntity
     * @throws InvalidRequestException
     * @throws ItemCreationException
     */
    @Operation(summary = "Add an item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ItemDTO.class))}),
            @ApiResponse(responseCode = "422", description = "Invalid Request",
                    content = @Content),
            @ApiResponse(responseCode = "417", description = "Failed to add an item",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> createItem(@Valid @RequestBody ItemDTO itemDTO, Errors errors) {
        log.info("Saving an Item");
        if (errors.hasErrors()) {
            String reqErrorDtls = errors.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining(","));
            log.info("Invalid request");
            log.error("Invalid request, errors - {}", reqErrorDtls);
            throw new InvalidRequestException(reqErrorDtls);
        }
        Optional<ItemDTO> result = itemService.saveItem(itemDTO);
        if (result.isPresent()) {
            log.info("Item created with product code - {}", result.get().getProductCode());
            return new ResponseEntity<>(result.get(), HttpStatus.CREATED);
        }
        log.info("Could not save an item");
        throw new ItemCreationException("Could not save an item");

    }

    /**
     * @param items
     * @return ResponseEntity
     * @throws ResponseStatusException
     */
    @Operation(summary = "Update item quantities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item Quantities updated",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "417", description = "Failed to update",
                    content = @Content)
    })
    @PutMapping
    public ResponseEntity<String> updateQuantity(@RequestBody Set<ItemDTO> items) {
        log.info("Processing Item quantities");
        log.debug("Processing Item quantities, items - {}", items);
        try {
            itemService.validateAndUpdateItems(items);
            log.info("Processing complete");
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            log.info("Validation failed, error - {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, e.getMessage());
        }
    }

    /**
     * @param productCode
     * @return ResponseEntity - which includes the requested item and an HTTP Status Code
     * @throws ItemNotFoundException
     */
    @Operation(summary = "Get an Item by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found an item",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ItemDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Item not found",
                    content = @Content)
    })
    @GetMapping("/{productCode}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable Long productCode) {
        log.info("Getting item details for productCode - {}", productCode);
        Optional<ItemDTO> result = itemService.getItemById(productCode);
        if (result.isPresent()) {
            log.info("Item Details - {}", result.get());
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        log.info("Item not found for productCode - {}", productCode);
        throw new ItemNotFoundException("Item not found for product code " + productCode);
    }

    /**
     * @return ResponseEntity - which includes list of items and an HTTP Status Code
     * @throws ItemNotFoundException
     */
    @Operation(summary = "Get all Items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Got all items",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = List.class))}),
            @ApiResponse(responseCode = "404", description = "No Items found",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<ItemDTO>> getItems() {
        log.info("Getting all orders");
        List<ItemDTO> result = itemService.getAllItems();
        if (!result.isEmpty()) {
            log.info("Successfully retrieved {} items", result.size());
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        log.info("No items found");
        throw new ItemNotFoundException("No items found");
    }
}
