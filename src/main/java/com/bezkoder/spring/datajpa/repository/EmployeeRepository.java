package com.bezkoder.spring.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bezkoder.spring.datajpa.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	@Query("select e from Employee e order by name")
	List <Employee> findOrderByName();
	@Query("from Employee where dep = :departmentId order by name")
	List <Employee> findOrderByDepartment(long departmentId);
}
