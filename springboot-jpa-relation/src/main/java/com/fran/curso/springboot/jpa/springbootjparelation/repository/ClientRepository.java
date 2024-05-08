package com.fran.curso.springboot.jpa.springbootjparelation.repository;

import com.fran.curso.springboot.jpa.springbootjparelation.entities.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
}
