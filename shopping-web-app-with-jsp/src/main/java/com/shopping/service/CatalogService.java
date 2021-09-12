package com.shopping.service;

import com.shopping.bean.Product;
import com.shopping.bean.ProductList;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CatalogService {
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "productDetails", fallbackMethod = "getAllProductsFallBack")
    public List<Product> getAllProducts() {
        return Objects.requireNonNull(Objects.requireNonNull(restTemplate.getForObject("https://catalog-service/products/", ProductList.class)).getProducts());
    }

    @CircuitBreaker(name = "productDetails", fallbackMethod = "getProductByCodeFallBack")
    public Product getProductByCode(String code) {
        return restTemplate.getForObject("https://catalog-service/products/code/" + code, Product.class);
    }

    public List<Product> getAllProductsFallBack(Exception e) {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(0L, "NA", "NA", "NA", 0));
        return productList;
    }

    public Product getProductByCodeFallBack(String productCode, Exception e) {
        return new Product(0L, productCode, "", "", 0);
    }

}
