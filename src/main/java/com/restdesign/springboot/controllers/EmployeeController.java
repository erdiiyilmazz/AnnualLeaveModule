package com.restdesign.springboot.controllers;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;

import com.restdesign.springboot.DTOs.EmployeeDTO;
import com.restdesign.springboot.entities.Employee;
import com.restdesign.springboot.services.EmployeeService;

import io.swagger.models.HttpMethod;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@RestController 
@Slf4j
@RequestMapping(value = "/employee", method = { RequestMethod.GET, RequestMethod.POST })
@Tag(name = "Employee", description = "Employee Api")
public class EmployeeController {
	@Value("{server.port}")
	private int port;
	
	private EmployeeService employeeService;
	
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
       
	@GetMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<EmployeeDTO>> getEmployeeList() {
		return new ResponseEntity<>(
				employeeService.dTOList(employeeService.getEmployeeList()), HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Object> newEmployee(@RequestBody EmployeeDTO employeeDTO) {
		try {
			employeeService.newEmployee(employeeDTO);
			employeeService.printCurrentTime();
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
	}
	
	@Order(1)
	public void run(String... args) {
		log.info("Employees found: ");
		for (Employee employee : employeeService.getEmployeeList()) {
			log.info(employee.getName());
		}
		
		log.info("");
		
		IntStream.range(1, 101).forEach(e -> {
			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setIdentity(Long.valueOf(19585430) + e);
			employeeDTO.setName("Firstname" + e);
			employeeDTO.setSurname("Lastname" + e);
			employeeDTO.setStartTime(LocalDateTime.now());

            RequestEntity requestEntity = new RequestEntity(employeeDTO, HttpMethod.POST, URI.create("http://localhost:" + port + "/employee"));
            
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.exchange(requestEntity, String.class);
		});
		
		log.info("Employees found: ");
		for (Employee employee : employeeService.getEmployeeList()) {
			log.info(employee.getName());
		}
		log.info("");
	}
}
