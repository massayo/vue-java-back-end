package com.bezkoder.spring.datajpa.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bezkoder.spring.datajpa.dto.EmployeeDto;
import com.bezkoder.spring.datajpa.model.Employee;
import com.bezkoder.spring.datajpa.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@Slf4j
@RequestMapping("/api")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    private ModelMapper modelMapper;
    
	public EmployeeController(final EmployeeService employeeService){
        this.employeeService = employeeService;
    }

  
  @GetMapping("/employees")
  public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
	  log.info("Retrieve all employees");
	  final List<Employee> result = employeeService.getAll();
	  final List<EmployeeDto> resultDto = result.stream()
              .map(this::convertToDto)
              .collect(Collectors.toList());
	  return new ResponseEntity<>(resultDto, HttpStatus.OK);
  }
  
  @GetMapping("/employees/{id}")
  public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") long id) {
	  Employee employee = employeeService.getEmployee(id);
	  EmployeeDto employeeDto = convertToDto(employee);
	  return new ResponseEntity<>(employeeDto, HttpStatus.OK);
  }

  @GetMapping("/departments/{id}/employees")
  public ResponseEntity<List<EmployeeDto>> getEmployeeByDepartment(@PathVariable("id") long id) {
	  final List<Employee> result = employeeService.getAllByDepartment(id);
	  final List<EmployeeDto> resultDto = result.stream()
              .map(this::convertToDto)
              .collect(Collectors.toList());
	  return new ResponseEntity<>(resultDto, HttpStatus.OK);
  }
   
  @PostMapping("/departments/{id}/employees")
  public ResponseEntity<EmployeeDto> createEmployee(@PathVariable("id") long id,@RequestBody EmployeeDto paramsDto) {
	  Employee params = convertToEntity(paramsDto);
	  Employee employee = employeeService.postEmployee(id, params);
	  EmployeeDto employeeDto = convertToDto(employee);
	  return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
  }
  
  @PutMapping("/employees/{id}/{did}")
  public ResponseEntity<HttpStatus> updateEmployee(@PathVariable("did") long did,@PathVariable("id") long id, @RequestBody EmployeeDto paramsDto) {
	  Employee employee = convertToEntity(paramsDto);
	  employeeService.updateEmployee(employee, id, did);
	  return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/employees/{id}")
  public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") long id) {
	  employeeService.deleteEmployee(id);
	  return new ResponseEntity<>(HttpStatus.OK);
  }
  
  private EmployeeDto convertToDto(Employee employee) {
	    EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
	    return employeeDto;
	}


	private Employee convertToEntity(EmployeeDto employeeDto) /*throws ParseException*/ {
	    Employee employee = modelMapper.map(employeeDto, Employee.class);
	       
	    return employee;
	}
}

