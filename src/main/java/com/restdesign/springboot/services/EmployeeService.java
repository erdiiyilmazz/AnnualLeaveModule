package com.restdesign.springboot.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.restdesign.springboot.DTOs.EmployeeDTO;
import com.restdesign.springboot.entities.Employee;
import com.restdesign.springboot.repositories.EmployeeRepository;
import com.restdesign.springboot.utils.Util;

import lombok.extern.slf4j.Slf4j;

@Service @Slf4j
@org.springframework.context.annotation.Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EmployeeService {
	private final EmployeeRepository employeeRepository;
	private String time = LocalDateTime.now().toString();
	
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public void printCurrentTime() {
		log.info("Time is now: " + time);
	}
	
	public List<EmployeeDTO> dTOList(List<Employee> employeeList) {
		return employeeList.stream().map(e -> Util.MODEL_MAPPER.map(e, EmployeeDTO.class)).collect(Collectors.toList());
	}
	
	public Employee entityEmployee(EmployeeDTO employeeDTO) {
		return Util.MODEL_MAPPER.map(employeeDTO, Employee.class);
	}
	
	public void newEmployee(EmployeeDTO newEmployeeDTO) {
		newEmployeeDTO.setStartTime(LocalDateTime.now());	
		log.info("Thread ID: " + Thread.currentThread().getId());	
		employeeRepository.save(entityEmployee(newEmployeeDTO));
	}
	
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
	
	public List<Employee> getEmployeeList() {
		return employeeRepository.findAll();
	}
}
