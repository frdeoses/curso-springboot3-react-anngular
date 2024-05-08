package com.fran.curso.springboot.jpa.springbootjparelation.repository;

import com.fran.curso.springboot.jpa.springbootjparelation.entities.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
