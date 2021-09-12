package com.shopping.service;

import com.shopping.bean.InventoryItem;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class InventoryService {
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "inventoryItemDetails", fallbackMethod = "getInventoryItemByCodeFallBack")
    public InventoryItem getInventoryItemByCode(String code) {
        return restTemplate.getForObject("https://inventory-service/inventories/code/" + code, InventoryItem.class);
    }

    public void updateInventoryItemQuantityByProductCode(String productCode, int availableQuantity) {
        InventoryItem inventoryItem = new InventoryItem(productCode, availableQuantity);
        restTemplate.put("https://inventory-service/inventories/", inventoryItem);
    }

    public InventoryItem getInventoryItemByCodeFallBack(String productCode, Exception e) {
        return new InventoryItem(productCode, 0);
    }

}
