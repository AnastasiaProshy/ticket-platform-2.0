package com.java.platform.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tickets")
public class Ticket 
{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		
		@NotNull
		@NotEmpty(message = "Title is mandatory")
		@Size(min=2,max=255, message = "Title must be between 2 and 255 characters")
		private String title;
		
		@NotNull
		@NotEmpty(message = "Description is mandatory")
		@Size(min=5,max=255, message = "Size must be between 2 and 255 characters")
		private String description;
		
		@Column(nullable = false)
		@NotNull(message = "Ticket status is required")
		private String status; // Default value
		
		@ManyToOne
		@NotNull(message = "An agent must be assigned to the ticket")
		@JoinColumn(name="user_id", nullable = false)
	    private User user;
		
		// у билета есть заметка
		 @OneToMany(mappedBy = "ticket", cascade = { CascadeType.REMOVE })
		 private List<Note> notes;
		 
//		// создаю инт доступных заметок
//		private Integer notesAvailable;
//		 
//		// найти доступные записи о билетах
//		@NotNull
//		@Max(500)
//		private Integer numberOfNotes;
		

		
		
		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Ticket() {
	        this.status = "To Do";	// Default value for status
	    }
		
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public List<Note> getNotes() {
			return notes;
		}

		public void setNotes(List<Note> notes) {
			this.notes = notes;
		}

//		public Integer getNotesAvailable() {
//			return notesAvailable;
//		}
//
//		public void setNotesAvailable(Integer notesAvailable) {
//			this.notesAvailable = notesAvailable;
//		}

		
}
