package com.fran.curso.springboot.error.springbooterror.services;

import com.fran.curso.springboot.error.springbooterror.models.domain.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> findAll();

    Optional<User> findById(Long id);
}
