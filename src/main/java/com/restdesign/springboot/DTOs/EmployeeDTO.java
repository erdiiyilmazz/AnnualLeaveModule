package com.restdesign.springboot.DTOs;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDTO implements Serializable{
	private Long identity;
	private String name;
	private String surname;
	private LocalDateTime startTime;
}
