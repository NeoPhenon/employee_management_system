package com.example.employee_management_system.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.javassist.expr.NewArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.employee_management_system.model.Employee;
import com.example.employee_management_system.service.EmployeeServiceImp;


@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeServiceImp employeeServiceImp;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	
	@RequestMapping( value = {"/employees" , "/"} , method =  RequestMethod.GET)
	public ModelAndView getAllEmployees() {
		ModelAndView modelAndView = new ModelAndView();
	  List<Employee> employees =  employeeServiceImp.getAllEmployees();
		modelAndView.addObject("employees",employees);
		modelAndView.setViewName("employees");
		return modelAndView;
	}
	
	@RequestMapping( value = "/createEmployee" , method =  RequestMethod.GET)
	public ModelAndView insertIntoEmployees() {
		ModelAndView modelAndView = new ModelAndView();
		Employee employee = new Employee();
		modelAndView.addObject("employee", employee);
		modelAndView.setViewName("createEmployee");
		return modelAndView;
	}
	
	@RequestMapping( value = "/saveEmployees" , method =  RequestMethod.POST)
	public ModelAndView saveEmployeeDatas(@Valid @ModelAttribute( name = "employee") Employee processedEmployeeDatas , RedirectAttributes redirectAttributes , BindingResult bindingResult ) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		if( bindingResult.hasErrors()) {
			modelAndView.setViewName("createEmployee");
			return modelAndView;
		}

		int result = employeeServiceImp.insertIntoEmployees(processedEmployeeDatas);
		if( result > 0 ) {
			redirectAttributes.addFlashAttribute("success_message","Employee Created Successfully!");
			LOGGER.info("Employee Created Umblemishedly!! : {} " + processedEmployeeDatas.getId()); // void doStuff( takes granted (value))
			modelAndView.setViewName("redirect:/employees");
		}
		else {
			redirectAttributes.addFlashAttribute("error_message" , " Something went wrong. please try again!");
			LOGGER.warn("Employee Creation Failed!! : {}" + processedEmployeeDatas.getId());
			modelAndView.setViewName("redirect:/createEmployee");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/employees/delete/{id}" , method =  RequestMethod.GET)
	public ModelAndView deleteEmployee(@PathVariable int id , RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView();
		int result = employeeServiceImp.deleteEmployee(id);
		
		if( result > 0 ) {
			redirectAttributes.addFlashAttribute("success_message","Employee Data Eradicated Successfully!");
			LOGGER.info("Employee Data Eradication Successed! : {}" + id );
			modelAndView.setViewName("redirect:/employees");
		}
		else {
			redirectAttributes.addFlashAttribute("error_message" , " Employee failed to be deleted!");
			modelAndView.setViewName("redire:/employees");
			LOGGER.warn("Employee Datas Eradication Failed!");
		}
		return modelAndView;
	}
	
	
	@RequestMapping( value = "/employees/edit/{id}" , method =  RequestMethod.GET)
	public ModelAndView editEmployee(@PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView();
	 Employee employee = employeeServiceImp.findEmployeeById(id);
	  modelAndView.addObject("employee", employee);
	  modelAndView.setViewName("editEmployee");
	  return modelAndView;
	}
	
	@RequestMapping( value = "/updateEmployee" , method =  RequestMethod.POST)
	public ModelAndView updateEmployeesDatas(@ModelAttribute(name = "employee") Employee updateProcessedEmployee , RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView();
	 int result = employeeServiceImp.updateEmployee(updateProcessedEmployee);
	 
	 if( result > 0 ) {
		redirectAttributes.addFlashAttribute("success_message","Employee Data Updated Successfully!");
		LOGGER.info(" Employee Datas Updated Successfully! : {}" + updateProcessedEmployee.getId());
		 modelAndView.setViewName("redirect:/employees");
	 }
	 else {
		 redirectAttributes.addFlashAttribute("error_message","Oops! Something went wrong on our end while updating the employee. Please Try Again!");
		 LOGGER.warn("Employee Datas updated Catastrophic! : {}" + updateProcessedEmployee.getId());
		 modelAndView.setViewName("redirect:/editEmployee");
	 }
	  return modelAndView;
	}
}
