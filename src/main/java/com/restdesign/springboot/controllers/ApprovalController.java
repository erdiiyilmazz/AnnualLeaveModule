package com.restdesign.springboot.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restdesign.springboot.DTOs.ApprovalDTO;
import com.restdesign.springboot.enums.StatusEnum;
import com.restdesign.springboot.services.ApprovalService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/approval")
@Tag(name = "Approval API", description = "")
public class ApprovalController {
	private ApprovalService approvalService;
	
	public ApprovalController(ApprovalService approvalService) {
		this.approvalService = approvalService;
	}
	
	@GetMapping
	private ResponseEntity<List<ApprovalDTO>> getList() {
		return new ResponseEntity<>(
				approvalService.DTOList(approvalService.getList()), HttpStatus.OK);				
	}
	
	@PostMapping
	private ResponseEntity<Object> create(@RequestBody ApprovalDTO approvalRequested) {
		try {
			approvalService.create(approvalRequested);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(approvalRequested, HttpStatus.OK);
	}
	
	@PutMapping(path = "{id}/{status}")
	private ResponseEntity<Object> updateStatus(@PathVariable Long id, @PathVariable StatusEnum status) {
		try {
			approvalService.updateStatus(id, status);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
}
