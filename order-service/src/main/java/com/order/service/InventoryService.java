package com.order.service;

import com.order.bean.InventoryItem;
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

    @CircuitBreaker(name = "inventoryItemDetails",fallbackMethod = "getInventoryItemByProductCodeFallBack")
    public InventoryItem getInventoryItemByProductCode(String productCode){
        return restTemplate.getForObject("https://inventory-service/inventories/order/code/" + productCode, InventoryItem.class);
    }
    public InventoryItem getInventoryItemByProductCodeFallBack(Exception e){
        return new InventoryItem(0L,"NA",0);
    }

}
