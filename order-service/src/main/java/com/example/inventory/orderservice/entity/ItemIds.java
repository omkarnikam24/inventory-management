package com.example.inventory.orderservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author omkar
 * @apiNote A persisting entity for ItemIds. This is not the actual Item Entity, it is only a mapper to an Order Entity.
 */
@Entity
public class ItemIds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productCode;

    private int quantity;

    public ItemIds() {
    }

    public ItemIds(Long productCode, int quantity) {
        this.productCode = productCode;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ItemIds{" +
                "id=" + id +
                ", productCode=" + productCode +
                ", quantity=" + quantity +
                '}';
    }
}
