package com.francurso.springboot.backend.backendproducts.repositories;

import com.francurso.springboot.backend.backendproducts.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

//@CrossOrigin(origins = "http://localhost:5173/")
@CrossOrigin(origins = "http://localhost:4200/")
@RepositoryRestResource(path = "products")
public interface ProductRepository extends CrudRepository<Product, Long> {
}
