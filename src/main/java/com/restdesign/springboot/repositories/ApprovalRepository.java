package com.restdesign.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restdesign.springboot.entities.Approval;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, Long>{
	
}
