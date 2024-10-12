package com.java.platform.security;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.java.platform.model.User;
import com.java.platform.repository.UserRepository;

@Service
public class DataBaseUserDetailsService implements UserDetailsService
{
	
	private @Autowired UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		// search the db if there is a user with the requested username
		Optional<User> user = userRepository.findByUsername(username);
		
		// if found - build a new instance of DB user details that concerns the user with the entered username
		if(user.isPresent())
		{
			return new DataBaseUserDetails(user.get());
		}
		// otherwise it throws a new exception of the type username not found exception
		else throw new UsernameNotFoundException("Username " + username + " not found!");
		
	}
}
