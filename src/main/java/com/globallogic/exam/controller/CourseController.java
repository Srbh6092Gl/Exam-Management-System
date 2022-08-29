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

import com.globallogic.exam.entity.Course;
import com.globallogic.exam.repository.CourseRepository;

@RestController
@RequestMapping("api/v1/course") 
public class CourseController {
	
	@Autowired
	CourseRepository coRepo;
	
	@GetMapping
	public List<Course> getAllCourses() {
		return coRepo.findAll();
	}
	
	@SuppressWarnings("static-access")
	@PostMapping
	public ResponseEntity<Course> addCourse(@RequestBody Course course){
		coRepo.save(course);
		return new ResponseEntity<>(HttpStatus.OK).ok(course);
	}

}
