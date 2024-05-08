package com.fran.curso.springboot.di.app.springboot.di.services;

import com.fran.curso.springboot.di.app.springboot.di.models.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAll();
    Product findById(Long id);
}
