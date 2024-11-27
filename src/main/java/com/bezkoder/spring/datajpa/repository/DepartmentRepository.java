package com.bezkoder.spring.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bezkoder.spring.datajpa.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	@Query("from Department order by name")
	List <Department> findOrderByName();
}
