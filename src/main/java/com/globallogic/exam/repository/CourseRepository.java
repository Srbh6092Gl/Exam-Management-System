package com.globallogic.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globallogic.exam.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
