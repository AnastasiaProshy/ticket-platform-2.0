package com.java.platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.platform.model.Category;

public interface CategoryRepository extends JpaRepository <Category, Integer> 
{
	//already has all the methods to perform CRUD operations for Tickets
	
}
