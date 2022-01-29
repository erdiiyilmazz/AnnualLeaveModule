package com.restdesign.springboot.approval;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

import com.restdesign.springboot.enums.StatusEnum;

import lombok.extern.slf4j.Slf4j;

@Aspect @Slf4j
public class ApprovalAspect {
	
	@After("execution(* com.restdesign.springboot.services.ApprovalService.updateStatus(..)")
	private void afterUpdateStatus(JoinPoint joinPoint) {
		Long id = (Long) joinPoint.getArgs()[0];
		StatusEnum statusEnum = (StatusEnum) joinPoint.getArgs()[1];
		log.info("Approval updated with id: " + id + " and status " + statusEnum);
	}
}
