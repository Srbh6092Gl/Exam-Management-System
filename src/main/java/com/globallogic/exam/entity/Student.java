package com.globallogic.exam.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@Entity
@Table( name="student",
		uniqueConstraints = {
				@UniqueConstraint(columnNames="email"),
				@UniqueConstraint(columnNames="username")
		})
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max=20)
	private String username;
	
	@NotBlank
	@Size(max=20)
	private String name;
	
	@NotBlank
	@Size(max=50)
	@Email(message = "Email invalid!")
	private String email;
	
	@NotBlank
	@Size(max = 120)
	private String password;
	
	private  boolean isPass = true;
	
	private double avg;
	
//	@ManyToOne(cascade = CascadeType.ALL)
	private RoleType role= RoleType.STUDENT;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Stream stream;

	public Student() {
	}
	

	public Student(Long id, String username, String name, String email, String password, RoleType role, Stream stream) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.stream = stream;
	}

	public Student( String username, String name, String email, String password, RoleType role, Stream stream) {
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.stream = stream;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public boolean isPass() {
		return isPass;
	}

	public void setPass(boolean isPass) {
		this.isPass = isPass;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	public Stream getStream() {
		return stream;
	}

	public void setStream(Stream stream) {
		this.stream = stream;
	}
	
}