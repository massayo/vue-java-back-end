package com.bezkoder.spring.datajpa.service;
import java.util.List;

import org.springframework.stereotype.Component;
import com.bezkoder.spring.datajpa.model.Department;

@Component
public interface DepartmentService {
    
	Department postDepartment(Department params);
	void updateDepartment(Department params, long id);
	Department getDepartment(long id);
	List <Department> getAll();
	void deleteDepartment(long id);
}
