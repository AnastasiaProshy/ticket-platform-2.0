package com.java.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.platform.model.User;
import com.java.platform.repository.UserRepository;

@Service	// the one who @serves the controller, puts between the controller and repository, so no longer have the repository in controller but in service
public class UserService {
	
	private @Autowired UserRepository userRepository;	// Repository injection
	
	
	public List<User> findAll()
	{
		return userRepository.findAll();
	}
	
	public User create(User user) {
	    return userRepository.save(user);
	}
	
	public List<User> findAvailableUsers() {
	    return userRepository.findByAvailableTrue();
	}
	
}
