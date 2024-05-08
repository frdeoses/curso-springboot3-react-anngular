package com.fran.curso.springboot.jpa.springbootjparelation.repository;

import com.fran.curso.springboot.jpa.springbootjparelation.entities.ClientDetails;
import com.fran.curso.springboot.jpa.springbootjparelation.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
