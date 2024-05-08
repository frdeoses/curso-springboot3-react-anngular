package com.fran.curso.springboot.app.services;

import com.fran.curso.springboot.app.entities.Role;
import com.fran.curso.springboot.app.entities.User;
import com.fran.curso.springboot.app.repositories.RoleRepository;
import com.fran.curso.springboot.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

//    @Transactional(readOnly = true)
//    @Override
//    public Optional<Product> findById(Long id) {
//        return repository.findById(id);
//    }

    @Transactional
    @Override
    public User save(User user) {

        Optional<Role> optionalRole = roleRepository.findByName("ROLE_USER");

        List<Role> roles = new ArrayList<>();

        optionalRole.ifPresent(roles::add);

        if (user.isAdmin()) {
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");

            optionalRoleAdmin.ifPresent(roles::add);
        }

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

//    @Override
//    @Transactional
//    public Optional<Product> update(Long id, Product product) {
//        Optional<Product> optionalProduct = repository.findById(id);
//
//        if(optionalProduct.isPresent()){
//            Product productDB = optionalProduct.orElseThrow();
//            productDB.setSku(product.getSku());
//            productDB.setName(product.getName());
//            productDB.setPrice(product.getPrice());
//            productDB.setDescription(product.getDescription());
//
//            return Optional.of(repository.save(productDB));
//        }
//
//        return optionalProduct;
//    }

//    @Transactional
//    @Override
//    public Optional<Product> delete(Long id) {
//
//        Optional<Product> optionalProduct = repository.findById(id);
//
//        optionalProduct.ifPresent(productDB -> repository.delete(productDB));
//
//        return optionalProduct;
//    }

//    @Transactional(readOnly = true)
//    @Override
//    public boolean existsBySku(String sku) {
//        return repository.existsBySku(sku);
//    }
}
