package com.catalog.resources;


import com.catalog.bean.Product;
import com.catalog.bean.ProductList;
import com.catalog.exceptions.ProductNotFoundException;
import com.catalog.service.CatalogServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@CrossOrigin
@RequestMapping(path = "/products")
public class CatalogResource {

    private CatalogServiceInterface catalogService;

    @Autowired
    public void setCatalogService(CatalogServiceInterface catalogService) {
        this.catalogService = catalogService;
    }


    @GetMapping(produces = "Application/json")
    ProductList listAllProducts() {
        return catalogService.getAllProducts();
    }


    @GetMapping(path = "/{id}", produces = "Application/json")
    ResponseEntity<Object> getProductById(@PathVariable("id") int id) {
        Product product =  catalogService.getProductById(id);
        if(product == null){
            throw new ProductNotFoundException("No Product With ID " + id + " Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @GetMapping(path = "/code/{code}", produces = "Application/json")
    ResponseEntity<Object> getProductByCode(@PathVariable("code") String code) {
        Product product =  catalogService.getProductByCode(code);
        if(product == null){
            throw new ProductNotFoundException("No Product With Code " + code + " Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @GetMapping(path = "/order/code/{code}", produces = "Application/json")
    Product getProductByCodeForOrder(@PathVariable("code") String code) {
        return catalogService.getProductByCode(code);
    }

    @PostMapping(produces = "Application/json", consumes = "Application/json")
    ResponseEntity<Object> saveProduct(@RequestBody Product product) {
        Product createdProduct = catalogService.insertProduct(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdProduct.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/{id}", produces = "Application/json")
    ResponseEntity<Object> deleteProduct(@PathVariable("id") int id) {
        catalogService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping(path = "/{id}/{price}", produces = "Application/json")
    ResponseEntity<Object> updateProductPrice(@PathVariable("id") int id, @PathVariable("price") Double price) {
        Product product =  catalogService.updateProductPrice(id, price);
        if(product == null){
            throw new ProductNotFoundException("No Product With ID " + id + " Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
}