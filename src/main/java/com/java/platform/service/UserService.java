package com.java.platform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.platform.model.Ticket;
import com.java.platform.model.User;
import com.java.platform.repository.UserRepository;

@Service	// the one who @serves the controller, puts between the controller and repository, so no longer have the repository in controller but in service
public class UserService {
	
	private @Autowired UserRepository userRepository;	// Repository injection
	
	
	public List<User> findAll()
	{
		return userRepository.findAll();
	}
	
	public User create(User user) 
	{
	    return userRepository.save(user);
	}
	
	public List<User> findFreeAgents() 
	{
	    return userRepository.findByAvailableAgent();
	}

	public Optional<User> findByUsername(String username) 
	{
		return userRepository.findByUsernameEquals(username);
	}

	public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
	}
	

    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
	

//	public Optional<User> findUser(String agents)
//	{
//		return userRepository.findUsers(agents);
//	}
	
}
