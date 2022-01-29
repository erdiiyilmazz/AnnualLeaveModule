package com.restdesign.springboot.interfaces;

import com.restdesign.springboot.entities.Approval;
import com.restdesign.springboot.enums.StatusEnum;
import com.restdesign.springboot.exceptions.ExceedingLeaveThreshold;
import com.restdesign.springboot.exceptions.StartDateException;

public interface ApprovalTypeInterface {
	void updateStatus(Approval approval, StatusEnum statusEnum) throws ExceedingLeaveThreshold, StartDateException;
}
