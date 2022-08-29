package com.globallogic.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globallogic.exam.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long>{

}
