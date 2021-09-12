package com.catalog.persistence;

import com.catalog.bean.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class ProductMapper  implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        String code = resultSet.getString("code");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        double price = resultSet.getDouble("price");

        return new Product(id,code,name,description,price);
    }
}
