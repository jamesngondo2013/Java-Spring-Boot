package com.luv2code.springboot.thymeleafdemo.springsecurity.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	// add a reference to our security data source
	
	@Autowired
	private DataSource securityDataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// use jdbc authentication ... oh yeah!!!
		
		auth.jdbcAuthentication().dataSource(securityDataSource);
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers("/customers/", "/customers", "/").denyAll()
		.antMatchers("/customers/list").hasAnyRole("EMPLOYEE","ADMIN", "MANAGER", "CEO")
		.antMatchers("/customers/manager/**").hasAnyRole("MANAGER")
		.antMatchers("/customers/admin/**").hasRole("ADMIN")
		.antMatchers("/customers/ceo/**").hasRole("CEO")
		.and()
		.formLogin()
			.loginPage("/customers/login")
			.loginProcessingUrl("/customers/authenticateTheUser")
			.defaultSuccessUrl("/customers/index")
			.permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/customers/access-denied");
		
	}
	
	 // create three users, leader, admin and user
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

   
		
    }
		
}






