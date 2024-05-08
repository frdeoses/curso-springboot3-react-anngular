package com.fran.curso.springboot.di.app.springboot.di.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fran.curso.springboot.di.app.springboot.di.models.Product;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ProductRepositoryJson implements IProductRepository {

    private final List<Product> productList;


    public ProductRepositoryJson(Resource resource) {
        productList = readValueJson(resource);
    }

    public ProductRepositoryJson() {
        Resource resource = new ClassPathResource("json/product.json");
        productList = readValueJson(resource);
    }

    private List<Product> readValueJson(Resource resource) {
        final List<Product> productList;
        ObjectMapper mapper = new ObjectMapper();

        try {
            productList = Arrays.asList(mapper.readValue(resource.getFile(), Product[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public Product findById(Long id) {
        return productList.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }
}
