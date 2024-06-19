package com.microservice.student.repositories;

import com.microservice.student.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

//    @Query("Select s from student s where s.courseId = :idCourse")
//    List<Student> findAllStudent(Long idCourse);

    List<Student> findAllByCourseId(Long idCourse);
}
