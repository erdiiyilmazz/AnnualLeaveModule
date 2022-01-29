package com.restdesign.springboot.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.restdesign.springboot.DTOs.LeaveDTO;
import com.restdesign.springboot.entities.Leave;
import com.restdesign.springboot.repositories.LeaveRepository;
import com.restdesign.springboot.utils.Util;


@Service
@org.springframework.context.annotation.Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LeaveService {
	private final LeaveRepository leaveRepository;
	
	public LeaveService(LeaveRepository leaveRepository) {
		this.leaveRepository = leaveRepository;
	}
	
	public List<Leave> getLeaveList() {
		return leaveRepository.findAll();
	}

	public List<LeaveDTO> dtoList(List<Leave> leaveList) {
		return leaveList.stream().map(e -> Util.MODEL_MAPPER.map(e, LeaveDTO.class)).collect(Collectors.toList());
	}
}
