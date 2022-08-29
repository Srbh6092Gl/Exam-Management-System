package com.globallogic.exam.securityjwt.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.globallogic.exam.entity.RoleType;
import com.globallogic.exam.entity.Student;

public class StudentDetailsImpl implements UserDetails {
	
	  private static final long serialVersionUID = 1L;

	  private Long id;

	  private String username;

	  private String email;

	  @JsonIgnore
	  private String password;

	  private Collection<? extends GrantedAuthority> authorities;

	  public StudentDetailsImpl(Long id, String username, String email, String password,
	      Collection<? extends GrantedAuthority> authorities) {
	    this.id = id;
	    this.username = username;
	    this.email = email;
	    this.password = password;
	    this.authorities = authorities;
	  }

	  public static StudentDetailsImpl build(Student student) {
	            
	    List<GrantedAuthority> authority = new ArrayList<>();
	    authority.add(new SimpleGrantedAuthority(RoleType.STUDENT.name()));

	    return new StudentDetailsImpl(
	       student.getId(), 
	       student.getUsername(), 
	       student.getEmail(),
	       student.getPassword(), 
	        authority);
	  }

	  @Override
	  public Collection<? extends GrantedAuthority> getAuthorities() {
	    return authorities;
	  }

	  public Long getId() {
	    return id;
	  }

	  public String getEmail() {
	    return email;
	  }

	  @Override
	  public String getPassword() {
	    return password;
	  }

	  @Override
	  public String getUsername() {
	    return username;
	  }

	  @Override
	  public boolean isAccountNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isAccountNonLocked() {
	    return true;
	  }

	  @Override
	  public boolean isCredentialsNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isEnabled() {
	    return true;
	  }

	  @Override
	  public boolean equals(Object o) {
	    if (this == o)
	      return true;
	    if (o == null || getClass() != o.getClass())
	      return false;
	    return id==((StaffDetailsImpl) o).getId();
	  }

}
