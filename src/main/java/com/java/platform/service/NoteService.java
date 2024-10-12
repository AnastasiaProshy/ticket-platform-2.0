package com.java.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.java.platform.model.Note;
import com.java.platform.repository.NoteRepository;

			// the one who @serves the controller, 
@Service	// puts between the controller and repository, so no longer have the repository in controller but in service
public class NoteService {
	
	private @Autowired NoteRepository noteRepository;
	
	
	public List<Note> findAllSortedByNoteCreatedDate()
	{
		return noteRepository.findAll(Sort.by("createdDate"));
	}
	
	public Note create(Note note)
	{
		return noteRepository.save(note);
	}

}
