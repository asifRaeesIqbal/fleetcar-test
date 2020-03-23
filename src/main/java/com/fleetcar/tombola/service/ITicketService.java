package com.fleetcar.tombola.service;

import java.util.List;

import com.fleetcar.tombola.model.Ticket;

/**
 * Ticket service API that allows a client to make ticket specific requests.
 * 
 * @author AI
 *
 */

public interface ITicketService {

	/**
	 * Buy a ticket that has not been sold or picked. A ticket that has been selected will then set as bought (sold) and 
	 * cannot be bought again.
	 * 
	 * @param userId   - the user who has bought this ticket
	 * 
	 * @return - Ticket
	 */
	Ticket buyTicket(String userId);

	/**
	 * Selects a winning ticket from all tickets that have not already been picked out. Once picked the winning ticket {@link Ticket} will
	 * be set to [Picked] thus not allowing it to be picked again or bought in case it has not been bought.
	 * 
	 * @return Ticket -  a ticket that has been picked
	 */
	Ticket pickWinningTicket();

	/**
	 * Get a a ticket based on its {@link Ticket.id}
	 * 
	 * @param ticketId   - the ticket id to check
	 * @Ticket    
	 */
	Ticket getTicket(Integer ticketId);

	/**
	 * Get all tickets for a user for which they have a chance of winning.
	 * 
	 * @param userId - user id
	 * @return
	 */
	List<Ticket> getCurrentTicketsForUser(String userId);

}