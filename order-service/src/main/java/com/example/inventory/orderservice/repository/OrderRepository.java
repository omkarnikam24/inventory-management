package com.example.inventory.orderservice.repository;

import com.example.inventory.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author omkar
 * @apiNote An Order Repository
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
