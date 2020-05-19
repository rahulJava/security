package com.luv2code.springsecurity.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		//add our users for in memory authentication
		
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication()
		.withUser(users.username("rahul").password("rratra@uncc.edu").roles("EMPLOYEE"))
		.withUser(users.username("karan").password("ratra").roles("EMPLOYEE","MANAGER"))
		.withUser(users.username("john").password("rambo").roles("EMPLOYEE","ADMIN"))
		.withUser(users.username("mary").password("mary123").roles("EMPLOYEE","MANAGER"));
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.authorizeRequests().
		antMatchers("/").hasRole("EMPLOYEE")
		.antMatchers("/leaders/**").hasRole("MANAGER")
		.antMatchers("/systems/**").hasRole("ADMIN")
		
		.and().formLogin().loginPage("/showMyFormLogin").loginProcessingUrl("/authenticateTheUser").permitAll().
		and().logout().permitAll()
		.and().exceptionHandling().accessDeniedPage("/access-denied");
	}

}
