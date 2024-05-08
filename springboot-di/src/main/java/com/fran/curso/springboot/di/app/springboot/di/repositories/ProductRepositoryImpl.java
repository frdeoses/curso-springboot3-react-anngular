package com.fran.curso.springboot.di.app.springboot.di.repositories;

import com.fran.curso.springboot.di.app.springboot.di.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Primary
//@SessionScope // Se destruye memoria una vez cierres navergador o postman
//@RequestScope  /// Se destruye memoria por cada peticion
@Repository("productList")
public class ProductRepositoryImpl implements IProductRepository {

    private final List<Product> data;

    public ProductRepositoryImpl() {
        this.data = Arrays.asList(
                new Product(1L, "Memoria corsair 32", 300L),
                new Product(2L, "CPU Inter Core i9", 850L),
                new Product(3L, "Teclado Razer", 180L),
                new Product(4L, "Mother board GigaByte", 490L)
        );
    }

    public List<Product> findAll() {
        return data;
    }

    public Product findById(Long id) {
        return data.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
