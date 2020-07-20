package com.example.inventory.item.repository;

import com.example.inventory.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author omkar
 * @apiNote An Item Repository
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
