package com.catalog.service;


import com.catalog.bean.Product;
import com.catalog.bean.ProductList;
import com.catalog.persistence.CatalogDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CatalogService implements CatalogServiceInterface {
    private CatalogDaoInterface catalogDao;

    @Autowired
    public void setCatalogDao(CatalogDaoInterface catalogDao) {
        this.catalogDao = catalogDao;
    }

    @Override
    public ProductList getAllProducts() {
        return catalogDao.getAllProducts();
    }

    @Override
    public Product insertProduct(Product product) {
        return catalogDao.insertProduct(product);
    }

    @Override
    public Product getProductById(int id) {
        return catalogDao.getProductById(id);
    }

    @Override
    public Product deleteProductById(int id) {
        return catalogDao.deleteProductById(id);
    }

    @Override
    public Product getProductByCode(String code) {
        return catalogDao.getProductByCode(code);
    }

    @Override
    public Product updateProductPrice(int id, Double price) {
        return catalogDao.updateProductPrice(id, price);

    }

}