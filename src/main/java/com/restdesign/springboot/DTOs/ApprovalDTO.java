package com.restdesign.springboot.DTOs;

import java.io.Serializable;

import com.fasterxml.jackson.databind.JsonNode;
import com.restdesign.springboot.enums.ApprovalEnum;
import com.restdesign.springboot.enums.StatusEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ApprovalDTO implements Serializable{
	public ApprovalDTO(ApprovalEnum valueOf, JsonNode readTree, StatusEnum valueOf2) {
		// TODO Auto-generated constructor stub
	}
	private ApprovalEnum topic;
	private JsonNode getDtoApproval;
	private StatusEnum status;
}
