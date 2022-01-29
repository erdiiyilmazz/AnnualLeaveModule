package com.restdesign.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restdesign.springboot.entities.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long>{
	
}
