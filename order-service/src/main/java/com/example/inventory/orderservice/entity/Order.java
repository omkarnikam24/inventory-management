package com.example.inventory.orderservice.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author omkar
 * @apiNote A persisting entity for Orders
 */
@Entity(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String customerName;
    private Date orderDate;
    private String shippingAddress;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<ItemIds> itemIds;

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

    public Set<ItemIds> getItemIds() {
        return itemIds;
    }

    public void setItemIds(Set<ItemIds> itemIds) {
        this.itemIds = itemIds;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", orderDate=" + orderDate +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", itemIds=" + itemIds +
                '}';
    }
}
