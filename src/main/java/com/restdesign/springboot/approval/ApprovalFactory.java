package com.restdesign.springboot.approval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.restdesign.springboot.enums.ApprovalEnum;
import com.restdesign.springboot.interfaces.ApprovalTypeInterface;

@Component
public class ApprovalFactory {
	private ApprovalLeave approvalLeave;
	
	@Autowired
	public ApprovalFactory(ApprovalLeave approvalLeave) {
		// TODO Auto-generated constructor stub
		this.approvalLeave = approvalLeave;
	}
	
	public ApprovalTypeInterface getApprovalTypeInterface(ApprovalEnum type) {
		if(type == ApprovalEnum.ANNUAL_LEAVE) {
			return (ApprovalTypeInterface) approvalLeave;
		}
		else {
			return null;
		}
	}
}
