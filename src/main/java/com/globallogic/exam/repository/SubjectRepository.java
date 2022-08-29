package com.globallogic.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globallogic.exam.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long>{

}
