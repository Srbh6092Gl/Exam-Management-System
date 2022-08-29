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

import com.globallogic.exam.entity.Question;
import com.globallogic.exam.repository.QuestionRepository;

@RestController
@RequestMapping("api/v1/questions") 
public class QuestionController {
	
	@Autowired
	QuestionRepository quesRepo;
	
	@GetMapping
	public List<Question> getAllQues() {
		return quesRepo.findAll();
	}
	
	@SuppressWarnings("static-access")
	@PostMapping
	public ResponseEntity<Question> addQues(@RequestBody Question ques){
		quesRepo.save(ques);
		return new ResponseEntity<>(HttpStatus.OK).ok(ques);
	}
}