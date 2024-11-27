package com.bezkoder.spring.datajpa.dto;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

//import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
    @Getter
    @Setter
public class DepartmentDto {
    	
    	//@JsonIgnore
    	private long id;

    	private String name;
    	@JsonManagedReference
        private List<EmployeeDto> employees;

}
