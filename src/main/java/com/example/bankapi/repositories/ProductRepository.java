package com.example.bankapi.repositories;

import com.example.bankapi.models.BlikModel;
import com.example.bankapi.models.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<ProductModel> getProductByEan(String code) {
        return
                jdbcTemplate.query(
                        "SELECT price, name FROM eancodes WHERE code =" + code,
                        BeanPropertyRowMapper.newInstance(ProductModel.class)
                );
    }
}
