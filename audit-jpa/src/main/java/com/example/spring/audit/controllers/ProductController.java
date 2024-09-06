package com.example.spring.audit.controllers;

import com.example.spring.audit.entity.ProductEntity;
import com.example.spring.audit.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> addProduct(@RequestBody ProductEntity product) {
        this.productRepository.save(product);
        return new ResponseEntity<>("Product create successful", HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateProduct(@PathVariable int id,
                                                @RequestBody ProductEntity product) {
        ProductEntity productFound = this.productRepository.findById(id).orElseThrow();
        productFound.setName(product.getName());
        productFound.setPrice(product.getPrice());
        this.productRepository.save(productFound);
        return new ResponseEntity<>("Product update successful", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        this.productRepository.deleteById(id);
        return new ResponseEntity<>("Product deleting successful", HttpStatus.OK);
    }
}
