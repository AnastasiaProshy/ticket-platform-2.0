package com.java.platform.controller.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.platform.model.Ticket;
import com.java.platform.service.TicketService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/tickets")
public class TicketRestController 
{
	
	private @Autowired TicketService ticketService;
	
	@GetMapping
	public List<Ticket> index(@RequestParam(name = "word", required = false) String word)
	{
		List<Ticket> result;
		
		if ( word != null && !word.isEmpty())
		{
			// if my keyword is well formed then ask the question with "word"
			result = ticketService.findAllByTitle(word);
		} 
		else
		{
			// if it isn't, set a standard index
			result = ticketService.findAll();
		}
		
		return result;	
	}
	
	// In order to wrap events, it allows us to handle the state of the question
	// such as http and response that we give ourselves
	@GetMapping("/{id}")
	public ResponseEntity<Ticket> show(@PathVariable("id") Integer id)
	{
		Optional<Ticket> ticket = ticketService.findById(id);
		
		if(ticket.isPresent())
		{
			return new ResponseEntity<>(ticket.get(), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	// call post on /tickets --> STORE
	@PostMapping
	public Ticket store(@Valid @RequestBody Ticket ticket)
	{
		Ticket newTicket = ticketService.create(ticket);
		
		return newTicket;
	}
	
	
	// put call on /tickets/{id} --> UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<Ticket> update(@Valid @RequestBody Ticket ticket,
						 @PathVariable("id") Integer id)
	{
		Optional<Ticket> oldTicket = ticketService.findById(id);
		
		if(oldTicket.isPresent())
		{
			Ticket foundTicket = ticketService.update(ticket);
			return new ResponseEntity<Ticket>(foundTicket, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	// call delete on /tickets/{id} --> DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<Ticket> delete(@PathVariable("id") Integer id)
	{
		Optional<Ticket> oldTicket = ticketService.findById(id);
		if(oldTicket.isPresent())
		{
			ticketService.deleteById(id);
			return new ResponseEntity<Ticket>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	
	

}
