package com.globallogic.exam.securityjwt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.globallogic.exam.entity.Staff;
import com.globallogic.exam.repository.StaffRepository;

public class StaffDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	StaffRepository staffRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Staff> staff = staffRepo.findStaffByUsername(username);
		if(!staff.isPresent())
			throw new UsernameNotFoundException("Staff not found with username:"+ username);
		
		return StaffDetailsImpl.build(staff.get());
	}

}
