package com.fran.curso.springboot.app.repositories;

import com.fran.curso.springboot.app.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
