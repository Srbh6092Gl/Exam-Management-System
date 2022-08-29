package com.globallogic.exam.entity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globallogic.exam.entity.Staff;
import com.globallogic.exam.repository.StaffRepository;

@Service
public class StaffServiceImpl implements StaffService {
	
	@Autowired
	StaffRepository staffRepo;

	@Override
	public List<Staff> findAllStaff() {
		return staffRepo.findAll();
	}

	@Override
	public Staff save(Staff staff) {
		return staffRepo.save(staff);
	}

	@Override
	public Staff findStaffByUsername(String username) throws Exception {
		Optional<Staff> staff= staffRepo.findStaffByUsername(username);
		if(staff.isEmpty())
			throw new Exception("No Staff with that username");
		return staff.get();
	}

	@Override
	public boolean existsByUsername(String username) {
		return staffRepo.existsByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		return staffRepo.existsByEmail(email);
	}

}
