package com.fleetcar.tombola.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fleetcar.tombola.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	
	@Query("SELECT t FROM Ticket t WHERE t.bought = FALSE AND t.picked= FALSE")
	public List<Ticket> findAllUnboughtAndUnpickedTickets();
	
	@Query("SELECT t FROM Ticket t WHERE t.bought = FALSE OR t.picked= FALSE")
	public List<Ticket> findAllUnboughtOrUnpickedTickets();
	
	@Query("SELECT t FROM Ticket t WHERE t.bought = true")
	public List<Ticket> findAllBoughtTickets();
	
	@Modifying(clearAutomatically = true)
	@Query("update Ticket t set t.bought = TRUE, t.user = :user where t.id = :id")
	public void updateTicketBought(@Param("id") Integer id, @Param("user") String user);
	
	@Query("SELECT t FROM Ticket t WHERE t.picked = false")
	public List<Ticket> findAllUnpickedTickets();
	
	@Modifying(clearAutomatically = true)
	@Query("update Ticket t set t.picked = TRUE where t.id = :id")
	public void updateTicketPicked(@Param("id") Integer id);
	
	@Query("SELECT t FROM Ticket t WHERE t.user = :user AND t.picked = FALSE")
	public List<Ticket> findAllUnpickedTicketsForUser(@Param("user") String userId);

}
