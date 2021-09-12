package com.catalog.persistence;

import com.catalog.bean.Product;
import com.catalog.bean.ProductList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CatalogDao implements CatalogDaoInterface {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ProductList getAllProducts() {
        ProductList productList = new ProductList();
        productList.setProducts(jdbcTemplate.query("SELECT * FROM products", new ProductMapper()));
        return productList;
    }

    public Product getLastProduct() {
        ProductList productList = new ProductList();
        productList.setProducts(jdbcTemplate.query("SELECT * FROM products ORDER BY id DESC LIMIT 1;", new ProductMapper()));
        if (!productList.getProducts().isEmpty()) {
            return productList.getProducts().get(0);
        }
        return null;
    }

    @Override
    public Product insertProduct(Product product) {
        int affectedRows = jdbcTemplate.update("INSERT INTO products(code, name, description, price) VALUE(?,?,?,?)", product.getCode(), product.getName(), product.getDescription(), product.getPrice());
        if (affectedRows > 0) {
            return getLastProduct();
        }
        return null;
    }

    @Override
    public Product getProductById(int id) {
        ProductList productList = new ProductList();
        productList.setProducts(jdbcTemplate.query("SELECT * FROM products WHERE id = ?;", new ProductMapper(), id));
        if (!productList.getProducts().isEmpty()) {
            return productList.getProducts().get(0);
        }
        return null;
    }

    @Override
    public Product getProductByCode(String code) {
        ProductList productList = new ProductList();
        productList.setProducts(jdbcTemplate.query("SELECT * FROM products WHERE code = ?;", new ProductMapper(), code));
        if (!productList.getProducts().isEmpty()) {
            return productList.getProducts().get(0);
        }
        return null;
    }

    @Override
    public Product updateProductPrice(int id, Double price) {
        int affectedRows = jdbcTemplate.update("UPDATE products SET price = ? WHERE id = ?", price, id);
        if (affectedRows > 0) {
            return getProductById(id);
        }
        return null;
    }

    @Override
    public Product deleteProductById(int id) {
        Product deletedProduct = getProductById(id);
        int affectedRows = jdbcTemplate.update("DELETE  FROM products WHERE id = ?", id);
        if (affectedRows > 0) {
            return deletedProduct;
        }
        return null;
    }
}
