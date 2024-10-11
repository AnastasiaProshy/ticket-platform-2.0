package com.java.platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.platform.model.User;

public interface UserRepository extends JpaRepository <User, Integer> 
{
	//already has all the methods to perform CRUD operations for Tickets
	
	public Optional<User> findByName(String name);
	
	List<User> findByAvailableTrue();
}
