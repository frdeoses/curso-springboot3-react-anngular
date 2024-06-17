package com.wannacode.product_microservice.controllers;

import com.wannacode.product_microservice.entities.ProductEntity;
import com.wannacode.product_microservice.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private IProductRepository productRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

//    @GetMapping
//    public ResponseEntity<List<ProductEntity>> getAllProduct() {
//        return ResponseEntity.ok(productRepository.findAll());
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void creteProduct(@RequestBody ProductEntity productEntity) {
        productRepository.save(productEntity);
    }


}
