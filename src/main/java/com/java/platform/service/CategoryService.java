package com.java.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.platform.model.Category;
import com.java.platform.model.User;
import com.java.platform.repository.CategoryRepository;
import com.java.platform.repository.UserRepository;

			// the one who @serves the controller, puts between the controller 
@Service	// and repository, so no longer have the repository in controller but in service
public class CategoryService {
	
	private @Autowired CategoryRepository categoryRepository;	// Repository injection
	
	public List<Category> findAll()
	{
		return categoryRepository.findAll();
	}

}
