package com.java.platform.model;

import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Formula;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User 
{
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	//@NotEmpty
	@Size(min=3,max=255)
	@Column(name = "username", nullable = false)
	private String username;
	
//	@NotNull
//	@NotEmpty
//	@Size(min=4,max=255)
//	@Column(name = "email", nullable = false)
//	private String email;
	
	@NotNull
	//@NotEmpty
	@Column(name = "password", nullable = false)
	private String password;
	
	// for availability flag
	@Column(nullable = false)
	private Boolean available = true;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;
	
	
	@OneToMany (mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Ticket> tickets;	// created ticket concept to connect to, so User has a ticket he is connected to 


	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



}
