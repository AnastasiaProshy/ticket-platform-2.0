package com.java.platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.platform.model.Ticket;


public interface TicketRepository extends JpaRepository <Ticket, Integer> 
{	//already has all the methods to perform CRUD operations for Tickets
	
	public List<Ticket> findByTitleContains(String title);
	
	public List<Ticket> findByTitleContainingIgnoreCaseOrderByTitleAsc(String title);
	
}
