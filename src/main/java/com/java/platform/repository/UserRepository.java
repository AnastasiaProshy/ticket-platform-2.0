package com.java.platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.platform.model.User;

public interface UserRepository extends JpaRepository <User, Integer> 
{
	//already has all the methods to perform CRUD operations for Tickets
	
	public Optional<User> findByUsername(String username);
	
	@Query("SELECT u FROM User u INNER JOIN u.roles r WHERE r.name = 'USER' AND u.available = true")
	List<User> findByAvailableAgent();
}
