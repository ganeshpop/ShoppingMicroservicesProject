package com.catalog.service;

import com.catalog.bean.Product;
import com.catalog.bean.ProductList;


public interface CatalogServiceInterface {

    ProductList getAllProducts();
    Product insertProduct(Product product);
    Product getProductById(int id);
    Product deleteProductById(int id);
    Product getProductByCode(String code);
    Product updateProductPrice(int id, Double price);

}