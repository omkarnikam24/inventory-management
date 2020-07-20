package com.example.inventory.orderservice.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author omkar
 * @apiNote A Data Transfer Object for Item
 */
public class ItemDTO {

    @NotNull(message = "Invalid product Code")
    private Long productCode;

    private String productName;

    @Min(value = 1, message = "Invalid product quantity")
    private int quantity;

    public ItemDTO(@NotNull(message = "Invalid product Code") Long productCode, @Min(value = 1, message = "Invalid product quantity") int quantity) {
        this.productCode = productCode;
        this.quantity = quantity;
    }

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "productCode=" + productCode +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
