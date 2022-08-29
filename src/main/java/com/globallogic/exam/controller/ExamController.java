package com.globallogic.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.exam.entity.Exam;
import com.globallogic.exam.repository.ExamRepository;

@RestController
@RequestMapping("api/v1/exam") 
public class ExamController {
	
	@Autowired
	ExamRepository examRepo;
	
	@GetMapping
	public List<Exam> getAllExam() {
		return examRepo.findAll();
	}
	
	@SuppressWarnings("static-access")
	@PostMapping
	public ResponseEntity<Exam> addExam(@RequestBody Exam exam){
		examRepo.save(exam);
		return new ResponseEntity<>(HttpStatus.OK).ok(exam);
	}
}