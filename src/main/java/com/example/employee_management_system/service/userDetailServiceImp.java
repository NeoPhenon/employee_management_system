package com.example.employee_management_system.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.employee_management_system.controller.EmployeeController;
import com.example.employee_management_system.model.userDetailModel;
import com.example.employee_management_system.repository.userDetailModelRepository;

@Service
public class userDetailServiceImp implements UserDetailsService{

	@Autowired
	private userDetailModelRepository userDetailModelRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	userDetailModel userDetailModel = userDetailModelRepository.loginProcess(username);
	
	 if( userDetailModel == null ) {
		 new UsernameNotFoundException(" User still stuck on tie to be found!");
		 LOGGER.error("user name is ultimately null!");
	 }
	 
	List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_"+userDetailModel.getAuthority()));
		return new User(userDetailModel.getUsername(), userDetailModel.getPassword(), authorities);
	}
}