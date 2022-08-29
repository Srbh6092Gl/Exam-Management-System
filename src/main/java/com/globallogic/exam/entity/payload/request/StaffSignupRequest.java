package com.globallogic.exam.entity.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class StaffSignupRequest {

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

	public StaffSignupRequest() {
	}

	public StaffSignupRequest(String username, String name, String email, String password) {
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
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

}