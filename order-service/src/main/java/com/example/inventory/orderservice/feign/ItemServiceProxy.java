package com.example.inventory.orderservice.feign;

import com.example.inventory.orderservice.dto.ItemDTO;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

/**
 * @author omkar
 * @apiNote A Feign Client used for accessing Item Service endpoints.
 */
@FeignClient("${service.name}")
@RibbonClient("${service.name}")
public interface ItemServiceProxy {

    @PutMapping("/item")
    ResponseEntity<String> updateAndRetrieveItems(@RequestBody Set<ItemDTO> items);
}
