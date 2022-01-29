package com.restdesign.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restdesign.springboot.DTOs.ApprovalDTO;
import com.restdesign.springboot.DTOs.LeaveDTO;
import com.restdesign.springboot.services.LeaveService;

import io.swagger.annotations.Tag;


@RestController
@RequestMapping("/annualleave")
//@Tag(description = "Annual leave rest api", name = "Annual Leave")
public class LeaveController {

	private LeaveService leaveService;
	
	@Autowired
	public LeaveController(LeaveService leaveService) {
		this.leaveService = leaveService;
	}
	
	@GetMapping
	private ResponseEntity<List<LeaveDTO>> getLeaveList() {
		return new ResponseEntity<>(
				leaveService.dtoList(leaveService.getLeaveList()), HttpStatus.OK);
	}
	
	@PostMapping
	private void requestLeave(@RequestBody ApprovalDTO approvalRequested) {
		
	}
}
