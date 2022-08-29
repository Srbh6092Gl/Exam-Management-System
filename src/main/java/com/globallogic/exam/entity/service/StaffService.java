package com.globallogic.exam.entity.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.globallogic.exam.entity.Staff;

@Repository
public interface StaffService{
	
	public List<Staff> findAllStaff();
	
	public Staff save(Staff staff);

	Staff findStaffByUsername(String username) throws Exception;

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);
}
