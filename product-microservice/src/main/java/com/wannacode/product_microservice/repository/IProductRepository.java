package com.wannacode.product_microservice.repository;

import com.wannacode.product_microservice.entities.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IProductRepository extends MongoRepository<ProductEntity, String> {
}
