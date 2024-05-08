package com.fran.curso.springboot.app.repositories;

import com.fran.curso.springboot.app.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

    boolean existsBySku(String sku);
}
