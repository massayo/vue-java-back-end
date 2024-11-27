package com.bezkoder.spring.datajpa.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

//import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
    @Getter
    @Setter
public class EmployeeDto {
	
	private long id;

    private String name;
    
    private long dep;
    
    private String dep_name;
    @JsonBackReference
    private DepartmentDto department;


}
