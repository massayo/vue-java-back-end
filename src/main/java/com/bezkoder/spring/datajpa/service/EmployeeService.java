package com.bezkoder.spring.datajpa.service;

import java.util.List;

import org.springframework.stereotype.Component;
import com.bezkoder.spring.datajpa.model.Employee;

@Component
public interface EmployeeService {
	
	Employee postEmployee(long departmentId, Employee employeeRequest);
	void updateEmployee(Employee params, long id, long departmentId);
	Employee getEmployee(long id);
	List <Employee> getAll();
	void deleteEmployee(long id);
	List <Employee> getAllByDepartment(long departmentId);

}
