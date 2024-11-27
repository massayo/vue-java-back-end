package com.bezkoder.spring.datajpa.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bezkoder.spring.datajpa.model.Department;
import com.bezkoder.spring.datajpa.model.Employee;
import com.bezkoder.spring.datajpa.repository.DepartmentRepository;
import com.bezkoder.spring.datajpa.repository.EmployeeRepository;
import com.bezkoder.spring.datajpa.service.EmployeeService;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
    private DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(final EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
     
	@Override
	public Employee postEmployee(long departmentId, Employee employeeRequest) {
		Department department = departmentRepository.findById(departmentId).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST));
		Employee employee = new Employee();
		employee.setDepartment(department);
		employee.setName(employeeRequest.getName());
		employee.setDep(departmentId);
		employee.setDep_name(department.getName());
		employeeRepository.save(employee);

	    return employee;
	  }
	
	@Override
	@Transactional
	public void updateEmployee(Employee params, long id, long departmentId) {
		final Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST));
		
		Department department = departmentRepository.findById(departmentId).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST));
		employee.setDepartment(department);
		employee.setName(params.getName());
	    employee.setDep(departmentId);
	    employee.setDep_name(department.getName());
		employeeRepository.save(employee);	
	}
    
	@Override
	public Employee getEmployee(long id) {
		final Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST));
		return (employee);
	}
	
	@Override
	public List <Employee> getAll(){
		return employeeRepository.findOrderByName();
	}
	
	@Override
	public List <Employee> getAllByDepartment(long departmentId){
		return employeeRepository.findOrderByDepartment(departmentId);
	}
	
	@Override
	public void deleteEmployee(long id) {
		final Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST));
		employeeRepository.delete(employee);
	}
	

}
