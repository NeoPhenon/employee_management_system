package com.example.employee_management_system.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.employee_management_system.model.userDetailModel;
import com.example.employee_management_system.service.accountCreationServiceImp;

@Controller
public class accountCreationController {
	
	@Autowired
	private accountCreationServiceImp accountCreationServiceImp;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(accountCreationController.class);
	
	@RequestMapping(value = "/createAccount" , method = RequestMethod.GET)
	public ModelAndView accountCreation(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userDetailModel", new userDetailModel());
		modelAndView.setViewName("createAccount");
		return modelAndView;
	}
	
	@RequestMapping(value = "/accountCreationProcess" , method = RequestMethod.POST)
	public ModelAndView saveAccountCreation( @Valid @ModelAttribute userDetailModel authorizedUserObj , RedirectAttributes redirectAttributes , BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		if( bindingResult.hasErrors()) {
			modelAndView.setViewName("createAccount");
			return modelAndView;
		}
		double output_val  = accountCreationServiceImp.accountCreationProcess(authorizedUserObj);
		String aspiringMessage = "Welcome Aboard! User Account Created!";
		String failiureHandlerMessage = "Oops! Something Went Wrong Creating the Accounting Entry";

		if( output_val == 2 ) {
			redirectAttributes.addFlashAttribute("success_message", aspiringMessage);
			LOGGER.info(" Account created blossomingly : {} " + authorizedUserObj.getUsername());
			modelAndView.setViewName("redirect:/employees");
		}
		else {
			redirectAttributes.addFlashAttribute("error_message", failiureHandlerMessage);
			LOGGER.warn(" Someing hallaciously wrong with Account Creation Entry! : {} " + authorizedUserObj.getUsername());
			modelAndView.setViewName("redirect:/createAccount");
		}
		
		return modelAndView;
	}
}
