package com.example.employee_management_system.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.employee_management_system.model.Employee;

@Mapper
public interface EmployeeRepository {
	public abstract List<Employee> getAllEmployees();
	public abstract int insertIntoEmployees(Employee employee);
	public abstract int deleteEmployee(int id);
	public abstract Employee findEmployeeById(int id);
	public abstract int updateEmployee(Employee employee);
}
