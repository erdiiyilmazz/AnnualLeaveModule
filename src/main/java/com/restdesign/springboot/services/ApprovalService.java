package com.restdesign.springboot.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.restdesign.springboot.DTOs.ApprovalDTO;
import com.restdesign.springboot.approval.ApprovalFactory;
import com.restdesign.springboot.entities.Approval;
import com.restdesign.springboot.enums.ApprovalEnum;
import com.restdesign.springboot.enums.StatusEnum;
import com.restdesign.springboot.exceptions.ExceedingLeaveThreshold;
import com.restdesign.springboot.exceptions.StartDateException;
import com.restdesign.springboot.interfaces.ApprovalTypeInterface;
import com.restdesign.springboot.repositories.ApprovalRepository;
import com.restdesign.springboot.utils.Util;

public class ApprovalService {
	private final ApprovalRepository approvalRepository;
	private ApprovalFactory approvalFactory;
	
	@Autowired
	public ApprovalService(ApprovalRepository approvalRepository, ApprovalFactory approvalFactory) {
		this.approvalRepository = approvalRepository;
		this.approvalFactory = approvalFactory;
	}
	
	public Approval toApproval(ApprovalDTO approvalDTO) {
		return Util.MODEL_MAPPER.map(approvalDTO, Approval.class);
	}
	
	public ApprovalDTO toApprovalDTO(Approval approval) {
		try {
			ApprovalDTO approvalDTO = new ApprovalDTO(
					ApprovalEnum.valueOf(approval.getTopic()),
					Util.OBJECT_MAPPER.readTree(approval.getDtoApproval()),
					StatusEnum.valueOf(approval.getStatus()),
					approval.getNote());
			return approvalDTO;
		} catch (JsonProcessingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Approval> getList() {
		return approvalRepository.findAll();
	}
	
	public void create(ApprovalDTO approvalDTO) {
		approvalRepository.save(toApproval(approvalDTO));
	}
	
	public List<ApprovalDTO> DTOList(List<Approval> approvalList) {
		return approvalList.stream().map(e -> toApprovalDTO(e)).collect(Collectors.toList());
	}
	
	
	public void updateStatus(Long id, StatusEnum status) throws ExceedingLeaveThreshold, StartDateException {
		Optional<Approval> approvalOptional = approvalRepository.findById(id);
		
		if (!approvalOptional.isPresent()) {
			return;
		}
		
		Approval approval = approvalOptional.get();
		
		ApprovalTypeInterface approvalTypeInterface = approvalFactory.getApprovalTypeInterface(ApprovalEnum.valueOf(approval.getTopic()));
	
		approvalTypeInterface.updateStatus(approval, status);
	}
}
