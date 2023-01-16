package com.example.bankapi.controllers;

import com.example.bankapi.models.ProductModel;
import com.example.bankapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class ProductController {
    //@Autowired
    ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("ean/{code}")
    public List<ProductModel> checkBlikCode(@PathVariable("code") String code){
        return productRepository.getProductByEan(code);

    }
}
