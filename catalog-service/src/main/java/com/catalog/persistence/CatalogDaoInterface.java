package com.catalog.persistence;


import com.catalog.bean.Product;
import com.catalog.bean.ProductList;


public interface CatalogDaoInterface {
    ProductList getAllProducts();
    Product insertProduct(Product product);
    Product getProductById(int id);
    Product getProductByCode(String code);
    Product updateProductPrice(int id, Double price);
    Product deleteProductById(int id);
}