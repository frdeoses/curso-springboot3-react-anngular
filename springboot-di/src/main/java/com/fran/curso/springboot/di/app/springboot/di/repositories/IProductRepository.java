package com.fran.curso.springboot.di.app.springboot.di.repositories;

import com.fran.curso.springboot.di.app.springboot.di.models.Product;

import java.util.List;

public interface IProductRepository {

    List<Product> findAll();
    Product findById(Long id);


}
