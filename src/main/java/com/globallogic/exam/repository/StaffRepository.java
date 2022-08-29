package com.globallogic.exam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globallogic.exam.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long>{

	Optional <Staff> findStaffByUsername(String username);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

}
