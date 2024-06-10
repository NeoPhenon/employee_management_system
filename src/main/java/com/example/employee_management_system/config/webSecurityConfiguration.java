package com.example.employee_management_system.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.employee_management_system.service.userDetailServiceImp;

@Configuration
@EnableWebSecurity
public class webSecurityConfiguration{
	
	@Autowired
	private userDetailServiceImp userDetailServiceImp;
	
	@Autowired
	public void globalConfiguration(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailServiceImp);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain filterChain( HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/login").anonymous()
		.antMatchers("/css/**","/img/**","/js/**").permitAll()
		.antMatchers("/users/**").hasRole("users")
		.antMatchers("/admin/**").hasRole("admin")
		.anyRequest().authenticated()
		.and()
		
		.formLogin()
		.loginPage("/login")
		.loginProcessingUrl("/loginProcess")
		.defaultSuccessUrl("/employees")
		.failureForwardUrl("/login?errorPoppedUp = true")
		
		.and()
		.logout()
		.logoutSuccessUrl("/login?logout=carriedout")
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID" ,"remember-me")
		
		.and()
		
		.rememberMe()
		.key("unique")
		.alwaysRemember(true)
		
		.and()
		.sessionManagement()
		.maximumSessions(1)
		.expiredUrl("/login?sessionExpired = true");
		
		return httpSecurity.build();
		
	}
}
