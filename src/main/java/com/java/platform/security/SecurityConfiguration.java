package com.java.platform.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
	
	
	@SuppressWarnings("removal")
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		http.authorizeHttpRequests()
		.requestMatchers("/tickets/create", "/tickets/edit/**").hasAuthority("ADMIN")		
		.requestMatchers("/user").hasAnyAuthority("ADMIN", "USER")
		.requestMatchers("/admin").hasAuthority("ADMIN")
		.requestMatchers("/**").permitAll()
		.and().formLogin()
		.and().logout()
		.and().exceptionHandling();
	
		return http.build();
	}
	
	@Bean
	DataBaseUserDetailsService userDetailService()
	{
		return new DataBaseUserDetailsService();
	}
	
	@Bean
	PasswordEncoder passwordEncoder()
	{
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}

}
