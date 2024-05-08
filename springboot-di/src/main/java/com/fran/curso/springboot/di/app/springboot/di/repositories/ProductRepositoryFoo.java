package com.fran.curso.springboot.di.app.springboot.di.repositories;

import com.fran.curso.springboot.di.app.springboot.di.models.Product;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

//@Primary
@Repository("productFoo")
public class ProductRepositoryFoo implements IProductRepository {
    @Override
    public List<Product> findAll() {
        return Collections.singletonList(new Product(1L, "Monitor Asus", 600L));
    }

    @Override
    public Product findById(Long id) {
        return new Product(1L, "Monitor Asus", 600L);
    }
}
