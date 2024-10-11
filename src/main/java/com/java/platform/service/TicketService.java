package com.java.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.platform.model.Ticket;
import com.java.platform.repository.TicketRepository;

@Service	// the one who @serves the controller, puts between the controller and repository, so no longer have the repository in controller but in service
public class TicketService {
	
	private @Autowired TicketRepository ticketRepository;	// Repository injection
	
	
	public List<Ticket> findAll()
	{
		return ticketRepository.findAll();
	}
	
	
	public List<Ticket> findByTitle(String title)
	{
		return ticketRepository.findByTitleContainingIgnoreCaseOrderByTitleAsc (title);
	}
	
	
	public List<Ticket> findAllByTitle(String searchedText)
	{
		return ticketRepository.findByTitleContains(searchedText);
	}
	
	
	public Ticket getById(Integer id)
	{
		return ticketRepository.findById(id).get();
	}
	
	
	public Ticket create(Ticket ticket)
	{
		return ticketRepository.save(ticket);
	}
	
	
	public Ticket update(Ticket ticket)
	{
		return ticketRepository.save(ticket);
	}
	
	
	public void deleteById(Integer id)
	{
		ticketRepository.deleteById(id);
	}

	
}
