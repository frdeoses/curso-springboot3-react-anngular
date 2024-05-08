package com.fran.curso.springboot.app.services;

import com.fran.curso.springboot.app.entities.Product;
import com.fran.curso.springboot.app.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    @Transactional
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> optionalProduct = repository.findById(id);

        if(optionalProduct.isPresent()){
            Product productDB = optionalProduct.orElseThrow();
            productDB.setSku(product.getSku());
            productDB.setName(product.getName());
            productDB.setPrice(product.getPrice());
            productDB.setDescription(product.getDescription());

            return Optional.of(repository.save(productDB));
        }

        return optionalProduct;
    }

    @Transactional
    @Override
    public Optional<Product> delete(Long id) {

        Optional<Product> optionalProduct = repository.findById(id);

        optionalProduct.ifPresent(productDB -> repository.delete(productDB));

        return optionalProduct;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsBySku(String sku) {
        return repository.existsBySku(sku);
    }
}
