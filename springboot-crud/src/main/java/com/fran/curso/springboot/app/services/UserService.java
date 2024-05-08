package com.fran.curso.springboot.app.services;

import com.fran.curso.springboot.app.entities.Product;
import com.fran.curso.springboot.app.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

//    Optional<Product> findById(Long id);
//
    User save(User user);
//
//    Optional<Product> update(Long id,Product product);
//
//    Optional<Product> delete(Long id);
//
    boolean existsByUsername(String username);


}
