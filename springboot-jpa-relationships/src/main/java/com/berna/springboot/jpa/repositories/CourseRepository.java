package com.berna.springboot.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.berna.springboot.jpa.entities.Course;

public interface CourseRepository extends CrudRepository<Course, Long>{

}
