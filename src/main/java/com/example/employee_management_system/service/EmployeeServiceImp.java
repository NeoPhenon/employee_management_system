package com.example.employee_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee_management_system.model.Employee;
import com.example.employee_management_system.repository.EmployeeRepository;

@Service
public class EmployeeServiceImp  implements EmployeeService{
	
	private final EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImp(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.getAllEmployees();
	}

	@Override
	public int insertIntoEmployees(Employee employee) {
		
		return employeeRepository.insertIntoEmployees(employee);
	}

	@Override
	public int deleteEmployee(int id) {
		return employeeRepository.deleteEmployee(id);
	}

	@Override
	public Employee findEmployeeById(int id) {
		return employeeRepository.findEmployeeById(id);
	}

	@Override
	public int updateEmployee(Employee employee) {
		return employeeRepository.updateEmployee(employee);
	}

}
