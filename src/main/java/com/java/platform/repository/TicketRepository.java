package com.java.platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.platform.model.Ticket;
import com.java.platform.model.User;
import com.java.platform.model.Category;



public interface TicketRepository extends JpaRepository <Ticket, Integer> 
{	//already has all the methods to perform CRUD operations for Tickets
	
	public List<Ticket> findByTitleContains(String title);
	
	public List<Ticket> findByTitleContainingIgnoreCaseOrderByTitleAsc(String title);

	List<Ticket> findAllByUser(User user);

	List<Ticket> findByTitleContainingAndUser(String title, User user);    

	// the query that retrieves the to do and in progress states related to the user
	@Query("SELECT t FROM Ticket t INNER JOIN t.user u WHERE (t.status = :status1 Or t.status = :status2) AND u.username = :user")
	List<Ticket> findByStatus(@Param("user") String user, @Param("status1") String status1,@Param("status2") String status2);

	
    @Query("SELECT t FROM Ticket t JOIN t.categories c WHERE c.name = :category")
    List<Ticket> findByCategoryName(@Param("category") String category);

	
	List<Ticket> findByStatusAllIgnoreCase(String status);

}
