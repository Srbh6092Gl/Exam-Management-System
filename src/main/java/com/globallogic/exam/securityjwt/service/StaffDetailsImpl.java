package com.globallogic.exam.securityjwt.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.globallogic.exam.entity.RoleType;
import com.globallogic.exam.entity.Staff;

public class StaffDetailsImpl implements UserDetails {
	
	  private static final long serialVersionUID = 1L;

	  private Long id;

	  private String username;

	  private String email;

	  @JsonIgnore
	  private String password;

	  private Collection<? extends GrantedAuthority> authorities;

	  public StaffDetailsImpl(Long id, String username, String email, String password,
	      Collection<? extends GrantedAuthority> authorities) {
	    this.id = id;
	    this.username = username;
	    this.email = email;
	    this.password = password;
	    this.authorities = authorities;
	  }

	  public static StaffDetailsImpl build(Staff staff) {
	            
	    List<GrantedAuthority> authority = new ArrayList<>();
	    authority.add(new SimpleGrantedAuthority(RoleType.STAFF.name()));

	    return new StaffDetailsImpl(
	        staff.getId(), 
	        staff.getUsername(), 
	        staff.getEmail(),
	        staff.getPassword(), 
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