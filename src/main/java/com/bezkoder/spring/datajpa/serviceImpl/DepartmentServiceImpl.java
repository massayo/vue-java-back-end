package com.bezkoder.spring.datajpa.serviceImpl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bezkoder.spring.datajpa.model.Department;
import com.bezkoder.spring.datajpa.repository.DepartmentRepository;
import com.bezkoder.spring.datajpa.service.DepartmentService;

import jakarta.transaction.Transactional;
@Service
public class DepartmentServiceImpl  implements DepartmentService{
	
	private final DepartmentRepository departmentRepository;
	
	public DepartmentServiceImpl(final DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}
     
	@Override
	public Department postDepartment(Department params) {
		long iddep = departmentRepository.save(params).getId();
		Department department = new Department();
		department.setId(iddep);
		department.setName(params.getName());
		return department;
	}
	
	@Override
	@Transactional
	public void updateDepartment(Department params, long id) {
		final Department department = departmentRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST));
		
		department.setName(params.getName());
			
		departmentRepository.save(department);		
	}
	
	@Override
	public Department getDepartment(long id) {
		final Department department = departmentRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST));
		return (department);
	}
	
	@Override
	public List <Department> getAll(){
		return departmentRepository.findOrderByName();
	}
	@Override
	public void deleteDepartment(long id) {
		final Department department = departmentRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST));
		departmentRepository.delete(department);
	}
}
