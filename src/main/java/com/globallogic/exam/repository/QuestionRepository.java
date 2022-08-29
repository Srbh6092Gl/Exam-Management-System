package com.globallogic.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globallogic.exam.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{

}
