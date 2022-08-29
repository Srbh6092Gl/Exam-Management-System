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

import com.globallogic.exam.entity.Subject;
import com.globallogic.exam.repository.SubjectRepository;

@RestController
@RequestMapping("api/v1/subjects") 
public class SubjectController {
	
	@Autowired
	SubjectRepository subRepo;
	
	@GetMapping
	public List<Subject> getAllSubjects() {
		return subRepo.findAll();
	}
	
	@SuppressWarnings("static-access")
	@PostMapping
	public ResponseEntity<Subject> addCourse(@RequestBody Subject sub){
		subRepo.save(sub);
		return new ResponseEntity<>(HttpStatus.OK).ok(sub);
	}
	
}