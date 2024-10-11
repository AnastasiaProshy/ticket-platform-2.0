package com.java.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.platform.model.Note;

public interface NoteRepository extends JpaRepository <Note, Integer> 
{
	//already has all the methods to perform CRUD operations for Tickets
	
}
