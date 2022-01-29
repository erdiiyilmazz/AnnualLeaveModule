package com.restdesign.springboot.approval;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.restdesign.springboot.DTOs.DateDTO;
import com.restdesign.springboot.entities.Approval;
import com.restdesign.springboot.entities.Employee;
import com.restdesign.springboot.entities.Leave;
import com.restdesign.springboot.enums.LeaveWorkingTimeEnum;
import com.restdesign.springboot.enums.StatusEnum;
import com.restdesign.springboot.enums.WeekendEnum;
import com.restdesign.springboot.exceptions.ExceedingLeaveThreshold;
import com.restdesign.springboot.exceptions.StartDateException;
import com.restdesign.springboot.utils.Util;

import jdk.internal.org.jline.utils.Log;

public class ApprovalLeave {
	private EntityManager entityManager;
	
	@Autowired
	public ApprovalLeave(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void updateStatus(Approval approval, StatusEnum status) throws ExceedingLeaveThreshold, StartDateException {
		if (StatusEnum.APPROVED != status) {
			approval.setStatus(status.name());
			entityManager.merge(approval);
			return;
		}
		
		DateDTO dateDTO = null;
		try {
			dateDTO = Util.OBJECT_MAPPER.readValue(approval.getDtoApproval(), DateDTO.class);
		} catch (JsonProcessingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		LocalDateTime fromDateTime = dateDTO.getFromDate();
		LocalDateTime toDateTime = dateDTO.getToDate();
		
		if (fromDateTime.isAfter(toDateTime)) {
			throw new StartDateException(fromDateTime.toString(), toDateTime.toString());
		}
		
		Long days = ChronoUnit.DAYS.between(fromDateTime.toLocalTime(), toDateTime.toLocalTime());
		
		Log.debug("Days in range: " + days);
		
		for (LocalDate localDate = fromDateTime.toLocalDate(); localDate.isBefore(toDateTime.toLocalDate()); localDate = localDate.plusDays(1)) {
			if(EnumUtils.isValidEnum(WeekendEnum.class, localDate.getDayOfWeek().name())) {
				days--;
				Log.debug("Weekend days are removed at: " + localDate + " " + localDate.getDayOfWeek().name());
			}
		}
		
		Log.debug("Days in working days" + days);
		
		Leave leave = new Leave();
		leave.setIdentity(dateDTO.getIdentity());
		
		Leave annualLeave = entityManager.createNamedQuery(Leave.FIND_BY_ID, Leave.class)
					.setParameter("identity", leave.getIdentity()).getSingleResult();
		
		Employee employee = entityManager.createNamedQuery(Employee.FIND_BY_ID, Employee.class)
					.setParameter("identity", annualLeave).getSingleResult();
		
		Long years = ChronoUnit.YEARS.between(employee.getDate().toLocalDate(), LocalDate.now());
		
		AtomicLong threshold = new AtomicLong();
		Arrays.stream(LeaveWorkingTimeEnum.values()).forEach(e -> {
            if (e.getMinYears() <= years && (e.getMaxYears() == null || years <= e.getMaxYears())) {
				threshold.set(e.getThreshold());
			}
		});
		
        Long usedLeave = annualLeave == null ? 0 : annualLeave.getLeaveUsed();
		Long usedLeaveTotal = annualLeave == null ? 0 : annualLeave.getTotalLeaveUsed();
        
		annualLeave.setLeaveUsed(usedLeave + days);
		annualLeave.setTotalLeaveUsed(usedLeaveTotal + days);
		entityManager.merge(annualLeave);
		
        approval.setStatus(status.name());
        entityManager.merge(approval);
 	}
}
