package com.berna.springboot.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.berna.springboot.jpa.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

}
