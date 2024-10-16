package com.java.platform.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java.platform.model.Note;
import com.java.platform.model.Ticket;
import com.java.platform.model.User;
import com.java.platform.service.CategoryService;
import com.java.platform.service.NoteService;
import com.java.platform.service.TicketService;
import com.java.platform.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tickets")
public class TicketController
{	
	private @Autowired TicketService ticketService;
	private @Autowired UserService userService;
	private @Autowired NoteService noteService;
	private @Autowired CategoryService categoryService;
	


	// INDEX TICKET
	@GetMapping() // make the page appear on the web
	public String index(Authentication authentication, Model model, @RequestParam(required = false) String search) 
	{   
	    // Get the user authenticated
	    Optional<User> agentOptional = userService.findByUsername(authentication.getName());
	    User loggedUser = agentOptional.get();
	    
	    List<Ticket> ticketList;

	    // If the user is admin, show all tickets
	    if ("Nastia".equalsIgnoreCase(loggedUser.getUsername()) || "Shura".equalsIgnoreCase(loggedUser.getUsername())){
	        if (search != null && !search.isEmpty()) {
	            model.addAttribute("ticketSearch", search);
	            ticketList = ticketService.findByTitle(search); // Retrieve all tickets that match the search title
	        } else {
	            ticketList = ticketService.findAll(); // Recover all tickets
	        }
	    } else {
	        // If the user is not admin, show all tickets
	        if (search != null && !search.isEmpty()) {
	            model.addAttribute("ticketSearch", search);
	            ticketList = ticketService.findByTitleAndUser(search, loggedUser); // Filter by title and user
	        } else {
	            ticketList = ticketService.findAllByUser(loggedUser); // Retrieve only user tickets
	        }
	    }
	    
	    // Add ticket list and username to template
	    model.addAttribute("tickets", ticketList);
	    model.addAttribute("username", loggedUser.getUsername());
	    model.addAttribute("loggedUser", loggedUser);
	    return "/tickets/index";
	}


	
	// SHOW TICKET
	@GetMapping("/{id}")
	public String show(@PathVariable("id") Integer id, Model model)
	{
		model.addAttribute("ticket", ticketService.getById(id));
		return "/tickets/show";
	}


	
	// CREATE TICKET
	@GetMapping("/create")
	public String create(Model model)
	{		
		model.addAttribute("ticket", new Ticket());
		model.addAttribute("agents", userService.findFreeAgents());
		model.addAttribute("categories", categoryService.findAll());
		
		return "tickets/create";
	}

	
	
	// STORE TICKET
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("ticket") Ticket formTicket, // recover the form you just filled out as a ticket template
						BindingResult bindingResult,
						RedirectAttributes attributes, // redirect messaging management mechanism
						Model model)
	{
		if (bindingResult.hasErrors())
		{
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("agents", userService.findFreeAgents()); // Restore agents in model, call from create' an expensive operation during error?
			return "tickets/create";
		}
		
	        formTicket.setStatus("To Do");
	    
			ticketService.create(formTicket);		
			
			attributes.addFlashAttribute("typeAlert", "success");
			attributes.addFlashAttribute("messageAlert", "Great news! '" + formTicket.getTitle() + "' has been added successfully");
			
            return "redirect:/tickets";
	}	
	
	
	
	// TICKET STATE
	@PostMapping("/updateStatus/{id}")
	public String updateStatus(@PathVariable("id") Integer id, 
							   @RequestParam("status") String newState, 
							   RedirectAttributes redirectAttributes) {
	    
		Ticket ticket = ticketService.getById(id);
		
	    if (ticket != null) {
	        ticket.setStatus(newState);
	        ticketService.create(ticket);
	        
	        redirectAttributes.addFlashAttribute("typeAlert", "success");
	        redirectAttributes.addFlashAttribute("messageAlert", "Ticket status updated successfully");
	    }
	    
	    return "redirect:/tickets/" + id;
	}
	
	
	
	@PostMapping("/updateBusy")
	public String availableUser(Authentication authentication, Model model,
								@RequestParam("busy") Boolean newState,
								RedirectAttributes redirectAttributes)
	{
		Optional<User> agentOptional = userService.findByUsername(authentication.getName());
	    User loggedUser = agentOptional.get();
	    
	    List<Ticket> ticket = ticketService.findByStatus(loggedUser.getUsername(), "To Do", "In Progress");
	    
	    if(ticket.isEmpty() )
	    {
	    	loggedUser.setAvailable(newState);
		    userService.create(loggedUser);
	       
	        redirectAttributes.addFlashAttribute("typeAlert", "success");
	        redirectAttributes.addFlashAttribute("messageAlert", "Your status is updated successfully to busy!");
		}
		else 
		{
			redirectAttributes.addFlashAttribute("typeAlert", "danger");
	        redirectAttributes.addFlashAttribute("messageAlert", "You have some ticket to complete first.");	
		}
			return "redirect:/tickets";
	}
	
	
	
	// EDIT
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model)
	{		
		model.addAttribute("ticket", ticketService.getById(id));
		model.addAttribute("agents", userService.findFreeAgents());
		model.addAttribute("categories", categoryService.findAll());
		return "/tickets/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("ticket") Ticket updateFormTicket, 
	                     BindingResult bindingResult,
	                     RedirectAttributes attributes,
	                     Model model) {
	    if (bindingResult.hasErrors()) {
	        model.addAttribute("agents", userService.findFreeAgents());
	        model.addAttribute("categories", categoryService.findAll());
	        return "tickets/edit";
	    }

	    ticketService.update(updateFormTicket); 
	    
	    attributes.addFlashAttribute("typeAlert", "success");
	    attributes.addFlashAttribute("messageAlert", "Ticket updated successfully!");

	    return "redirect:/tickets";
	}

	
	
	// DELETE
	@PostMapping ("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes)
	{
		ticketService.deleteById(id);
		
		redirectAttributes.addFlashAttribute("typeAlert", "danger");
		redirectAttributes.addFlashAttribute("messageAlert", "The ticket has been removed successfully");
		
		return "redirect:/tickets";
	}
	
	

	// CREATE NOTE
	@GetMapping("/{id}/notes/create")
	public String note(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes, Authentication authentication)
	{
		Optional<User> agentOptional = userService.findByUsername(authentication.getName());
	    User loggedUser = agentOptional.get();
	    
		Ticket ticket = ticketService.getById(id);
		Note note = new Note(); // Creating a new note
		note.setCreatedDate(LocalDate.now()); // Set with today's date
		note.setUser(loggedUser);
		note.setTicket(ticket); // manage the transition as a note instead of a ticket. This way, I can use the get and set methods, simplifying the process
		model.addAttribute("note", note); // has been created as a note so add the element to the model and set to 'note' 
		return "notes/create";
	}
	
}