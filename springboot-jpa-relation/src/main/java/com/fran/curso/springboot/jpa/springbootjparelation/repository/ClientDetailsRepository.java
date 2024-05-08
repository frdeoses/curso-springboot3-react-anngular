package com.fran.curso.springboot.jpa.springbootjparelation.repository;

import com.fran.curso.springboot.jpa.springbootjparelation.entities.ClientDetails;
import org.springframework.data.repository.CrudRepository;

public interface ClientDetailsRepository extends CrudRepository<ClientDetails, Long> {
}
