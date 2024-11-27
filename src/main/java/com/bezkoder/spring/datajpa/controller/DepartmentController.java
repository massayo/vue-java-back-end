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

import com.bezkoder.spring.datajpa.dto.DepartmentDto;
import com.bezkoder.spring.datajpa.model.Department;
import com.bezkoder.spring.datajpa.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@Slf4j
@RequestMapping("/api")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    private ModelMapper modelMapper;
    
	public DepartmentController(final DepartmentService departmentService){
        this.departmentService = departmentService;
    }

  
  @GetMapping("/departments")
  public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
	  log.info("Retrieve all departments");
	  final List<Department> result = departmentService.getAll();
      final List<DepartmentDto> resultDto = result.stream()
              .map(this::convertToDto)
              .collect(Collectors.toList()); 
	  return new ResponseEntity<>(resultDto, HttpStatus.OK);
  }
  
  @GetMapping("/departments/{id}")
  public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") long id) {
	  Department department = departmentService.getDepartment(id);
	  DepartmentDto departmentDto = convertToDto(department);
	  return new ResponseEntity<>(departmentDto, HttpStatus.OK);
  }

  @PostMapping("/departments")
  public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto paramsDto) {
	  
		  Department params = convertToEntity(paramsDto);
		  Department department = departmentService.postDepartment(params);
		  DepartmentDto departmentDto = convertToDto(department);
	  
	  return new ResponseEntity<>(departmentDto, HttpStatus.CREATED);
  }

  @PutMapping("/departments/{id}")
  public ResponseEntity<HttpStatus> updateDepartment(@PathVariable("id") long id, @RequestBody DepartmentDto paramsDto) {
	  Department department = convertToEntity(paramsDto);
	  //department.setName(paramsDto.getName());
	  departmentService.updateDepartment(department, id);
	  return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/departments/{id}")
  public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable("id") long id) {
	  departmentService.deleteDepartment(id);
	  return new ResponseEntity<>(HttpStatus.OK);
  }
  
  private DepartmentDto convertToDto(Department department) {
	    DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);
	    //departmentDto.setSubmissionDate(department.getSubmissionDate(), 
	        //userService.getCurrentUser().getPreference().getTimezone());
	    return departmentDto;
	}


	private Department convertToEntity(DepartmentDto departmentDto) /*throws ParseException*/ {
	    Department department = modelMapper.map(departmentDto, Department.class);
	    //department.setSubmissionDate(departmentDto.getSubmissionDateConverted(
	      //userService.getCurrentUser().getPreference().getTimezone()));
	 
	    if (departmentDto.getId() > 0) {
	        //Department oldDepartment = departmentService.getDepartment(departmentDto.getId());
	        //department.setName(oldDepartment.getName());
	    }
	    return department;
	}
}

