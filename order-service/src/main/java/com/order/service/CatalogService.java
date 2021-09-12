package com.order.service;

import com.order.bean.InventoryItem;
import com.order.bean.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatalogService {
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "productDetails",fallbackMethod = "getProductByProductCodeFallBack")
    public Product getProductByProductCode(String productCode){
        return restTemplate.getForObject("https://catalog-service/products/order/code/" + productCode, Product.class);
    }
    public Product getProductByProductCodeFallBack(Exception e){
        return new Product(0L,"NA","NA","NA",0);
    }
}
