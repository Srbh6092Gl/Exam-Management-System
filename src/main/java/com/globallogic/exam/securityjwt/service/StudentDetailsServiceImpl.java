package com.globallogic.exam.securityjwt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.globallogic.exam.entity.Student;
import com.globallogic.exam.repository.StudentRepository;

public class StudentDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	StudentRepository stuRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Student> student = stuRepo.findStudentByUsername(username);
		if(!student.isPresent())
			throw new UsernameNotFoundException("Staff not found with username:"+ username);
		
		return StudentDetailsImpl.build(student.get());
	}
}
