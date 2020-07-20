package com.example.inventory.orderservice.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

/**
 * @author omkar
 * @apiNote A Data Transfer Object for Order
 */
public class OrderDTO {

    private Long orderId;

    @NotNull(message = "Invalid Customer Name")
    @Pattern(regexp = "^[a-zA-Z]+", message = "Customer name cannot be empty and must contain only alphabets")
    @Size(max = 100, message = "Customer Name must not contain more than 100 characters")
    private String customerName;

    @NotNull(message = "Invalid order date")
    private Date orderDate;

    @NotNull(message = "Invalid Shipping Address")
    @Size(max = 255, message = "Shipping Address must not contain more than 255 characters")
    private String shippingAddress;

    @Valid
    @NotNull
    private Set<ItemDTO> items;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Set<ItemDTO> getItems() {
        return items;
    }

    public void setItems(Set<ItemDTO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", orderDate=" + orderDate +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", itemIds=" + items +
                '}';
    }
}
