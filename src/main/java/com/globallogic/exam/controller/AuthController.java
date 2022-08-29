package com.globallogic.exam.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.exam.entity.RoleType;
import com.globallogic.exam.entity.Staff;
import com.globallogic.exam.entity.Student;
import com.globallogic.exam.entity.payload.request.LoginRequest;
import com.globallogic.exam.entity.payload.request.StaffSignupRequest;
import com.globallogic.exam.entity.payload.request.StudentSignupRequest;
import com.globallogic.exam.entity.payload.response.JwtResponse;
import com.globallogic.exam.entity.payload.response.MessageResponse;
import com.globallogic.exam.entity.service.StaffService;
import com.globallogic.exam.repository.StudentRepository;
import com.globallogic.exam.securityjwt.jwt.JwtUtils;
import com.globallogic.exam.securityjwt.service.StaffDetailsImpl;
import com.globallogic.exam.securityjwt.service.StudentDetailsImpl;

@RestController
@RequestMapping("api/v1/auth") 
public class AuthController {

	  @Autowired
	  AuthenticationManager authenticationManager;

	  @Autowired
	  StaffService staffService;

	  @Autowired
	  StudentRepository stuRepo;

	  @Autowired
	  PasswordEncoder encoder;

	  @Autowired
	  JwtUtils jwtUtils;
	  
	  @GetMapping("/staff")
	  public List<Staff> getAllStaff(){
		  return staffService.findAllStaff();
	  }
	  
	  @GetMapping("/student")
	  public List<Student> getAllStudent(){
		  return stuRepo.findAll();
	  }
	  
	  @PostMapping("/signin/staff")
	  public ResponseEntity<?> authenticateStaff(@Valid @RequestAttribute LoginRequest loginRequest) {

	    Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    String jwt = jwtUtils.generateStaffJwtToken(authentication);
	    
	    StaffDetailsImpl userDetails = (StaffDetailsImpl) authentication.getPrincipal();
	    String role = userDetails.getAuthorities().stream()
		        .map(item -> item.getAuthority())
		        .collect(Collectors.toList()).get(0);

	    return ResponseEntity.ok(new JwtResponse(jwt, 
	                         userDetails.getId(), 
	                         userDetails.getUsername(), 
	                         userDetails.getEmail(), 
	                         role));
	  }
	  
	  @PostMapping("/signin/student")
	  public ResponseEntity<?> authenticateStudent(@Valid @RequestAttribute LoginRequest loginRequest) {

	    Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    String jwt = jwtUtils.generateStudentJwtToken(authentication);
	    
	    StudentDetailsImpl userDetails = (StudentDetailsImpl) authentication.getPrincipal();
	    String role = userDetails.getAuthorities().stream()
		        .map(item -> item.getAuthority())
		        .collect(Collectors.toList()).get(0);

	    return ResponseEntity.ok(new JwtResponse(jwt, 
	                         userDetails.getId(), 
	                         userDetails.getUsername(), 
	                         userDetails.getEmail(), 
	                         role));
	  }
	  
	  @PostMapping("/signup/staff")
	  public ResponseEntity<?> registerStaff(@Valid @RequestBody StaffSignupRequest signUpRequest) {
		  if (staffService.existsByUsername(signUpRequest.getUsername())) {
			  return ResponseEntity
					  .badRequest()
					  .body(new MessageResponse("Error: Username is already taken!"));
		  }
		  
		  if (staffService.existsByEmail(signUpRequest.getEmail())) {
			  return ResponseEntity
	    			.badRequest()
	    			.body(new MessageResponse("Error: Email is already in use!"));
		  }

	    // Create new staff's account
	    Staff staff = new Staff(signUpRequest.getUsername(),
	    		signUpRequest.getName(),
	    		signUpRequest.getEmail(),
	               encoder.encode(signUpRequest.getPassword()),
	               RoleType.STAFF);
	    
	    staffService.save(staff);

	    return ResponseEntity.ok(new MessageResponse("Staff registered successfully!"));
	  }
	  
	  @PostMapping("/signup/student")
	  public ResponseEntity<?> registerStudent(@Valid @RequestBody StudentSignupRequest signUpRequest) {
		  if (stuRepo.existsByUsername(signUpRequest.getUsername())) {
			  return ResponseEntity
					  .badRequest()
					  .body(new MessageResponse("Error: Username is already taken!"));
		  }
		  
		  if (stuRepo.existsByEmail(signUpRequest.getEmail())) {
			  return ResponseEntity
	    			.badRequest()
	    			.body(new MessageResponse("Error: Email is already in use!"));
		  }

	    // Create new student's account
	    Student student = new Student(signUpRequest.getUsername(),
	    		signUpRequest.getName(),
	    		signUpRequest.getEmail(),
	               encoder.encode(signUpRequest.getPassword()),
	               RoleType.STUDENT,
	               signUpRequest.getStream());
	    
	    stuRepo.save(student);

	    return ResponseEntity.ok(new MessageResponse("Student registered successfully!"));
	  }
	  
}