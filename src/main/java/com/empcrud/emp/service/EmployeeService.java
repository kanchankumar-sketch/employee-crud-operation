package com.empcrud.emp.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empcrud.emp.Employee.Employee;
import com.empcrud.emp.Repo.EmployeeRepo;
import com.empcrud.emp.exception.UserNotFoundException;
@Service
@Transactional
public class EmployeeService {
	private final EmployeeRepo employeeRepo;
	
	@Autowired
	public EmployeeService(EmployeeRepo employeeRepo)
	{
		this.employeeRepo=employeeRepo;
	}
	
	//add employee here
	public Employee addEmployee(Employee employee)
	{
		employee.setEmployeeCode(UUID.randomUUID().toString());
		return employeeRepo.save(employee);
	}
	
	public List<Employee> findAllEmployees()
	{
		return employeeRepo.findAll();
	}
	
	public Employee updateEmployee(Employee employee)
	{
		return employeeRepo.save(employee);
	}
	
	public Employee findEmployeeById(Long id)
	{
		return employeeRepo.findEmployeeById(id).orElseThrow(()->new UserNotFoundException("User by id"+id+"was not found"));
	}
	
	
	public void deleteEmployeeById(Long id)
	{
		employeeRepo.deleteEmployeeById(id);
	}
	
}
