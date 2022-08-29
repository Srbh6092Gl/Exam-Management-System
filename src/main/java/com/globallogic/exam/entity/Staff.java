package com.globallogic.exam.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@Entity
@Table( name="staff",
		uniqueConstraints = {
				@UniqueConstraint(columnNames="email"),
				@UniqueConstraint(columnNames="username")
		})
public class Staff {
	
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
	
//	@ManyToOne(cascade = CascadeType.ALL)
	private RoleType role=RoleType.STAFF;

	public Staff() {
	}

	public Staff(Long id, String username, String name, String email, String password, RoleType role) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	

	public Staff(String username, String name, String email, String password, RoleType role) {
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
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

	public void setPassword(String password) {
		this.password = password;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}
	
}
