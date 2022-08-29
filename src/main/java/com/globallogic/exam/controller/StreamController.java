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

import com.globallogic.exam.entity.Stream;
import com.globallogic.exam.repository.StreamRepository;

@RestController
@RequestMapping("api/v1/stream") 
public class StreamController {
	
	@Autowired
	StreamRepository strRepo;
	
	@GetMapping
	public List<Stream> getAllSubjects() {
		return strRepo.findAll();
	}
	
	@SuppressWarnings("static-access")
	@PostMapping
	public ResponseEntity<Stream> addCourse(@RequestBody Stream str){
		strRepo.save(str);
		return new ResponseEntity<>(HttpStatus.OK).ok(str);
	}
	
}