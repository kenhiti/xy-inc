/*package com.zup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception{
		builder.userDetailsService(userDetailsService());	
		builder.inMemoryAuthentication().withUser("kenhiti").password("123").authorities("DEVELOPER");		
	}	 
	
	
	protected void configure(HttpSecurity http) throws Exception{
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll()			
			.anyRequest().authenticated()
		.and()
			.httpBasic();		
	}
}*/