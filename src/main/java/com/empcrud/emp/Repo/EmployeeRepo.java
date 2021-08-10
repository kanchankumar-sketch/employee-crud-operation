package com.empcrud.emp.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empcrud.emp.Employee.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,Long>{

	void deleteEmployeeById(Long id);

	Optional<Employee> findEmployeeById(Long id);

}
