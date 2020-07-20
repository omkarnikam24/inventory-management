package com.example.inventory.item.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * @author omkar
 * @apiNote A Data Transfer Object for Item
 */
public class ItemDTO {

    //@NotNull(message = "Invalid Product Code")
    private Long productCode;

    @NotNull(message = "Invalid Product Name")
    @Pattern(regexp = "^[a-zA-Z]+", message = "Product Name cannot be empty and must contain only alphabets")
    @Size(max = 100, message = "Product Name must not contain more than 100 characters")
    private String productName;

    @Min(value = 1, message = "Invalid Product Quantity")
    private int quantity;

    public ItemDTO() {
    }

    public ItemDTO(Long productCode, @NotNull(message = "Invalid Product Name") @Pattern(regexp = "^[a-zA-Z]+", message = "Product Name cannot be empty and must contain only alphabets") @Size(max = 100, message = "Product Name must not contain more than 100 characters") String productName, @Min(value = 1, message = "Invalid Product Quantity") int quantity) {
        this.productCode = productCode;
        this.productName = productName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDTO dto = (ItemDTO) o;
        return productCode.equals(dto.productCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productCode);
    }
}
