package com.example.employee_management_system.service;

import java.util.List;

import com.example.employee_management_system.model.Employee;

public interface EmployeeService {
	public abstract List<Employee> getAllEmployees();
	public abstract int insertIntoEmployees(Employee employee);
	public abstract int deleteEmployee(int id);
	public abstract Employee findEmployeeById(int id);
	public abstract int updateEmployee(Employee employee);
}
