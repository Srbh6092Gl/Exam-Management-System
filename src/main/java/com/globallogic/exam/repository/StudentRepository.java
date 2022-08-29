package com.globallogic.exam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globallogic.exam.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

	Optional<Student> findStudentByUsername(String username);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

}
