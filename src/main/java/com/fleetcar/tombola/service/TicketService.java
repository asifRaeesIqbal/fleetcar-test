package com.fleetcar.tombola.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fleetcar.tombola.model.Ticket;
import com.fleetcar.tombola.model.TicketPrice;
import com.fleetcar.tombola.repository.TicketRepository;

@Service
public class TicketService implements ITicketService {

	private Object MUTEX = new Object();

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private TicketPrice ticketPrice;

	@Transactional
	@Override
	public Ticket buyTicket(String user) {
		Ticket ticket = null;
		
		synchronized (MUTEX) {
			List<Ticket> availableTickets = ticketRepository.findAllUnboughtAndUnpickedTickets();
			if (availableTickets.isEmpty()) {
				throw new IllegalStateException("No more tickets available!");
			}
			Collections.shuffle(availableTickets);
			ticket = availableTickets.get(0);
			ticketRepository.updateTicketBought(ticket.getId(), user);
			ticket = getTicket(ticket.getId());
			
			List<Ticket> soldTickets = ticketRepository.findAllBoughtTickets();
			if(soldTickets.size() % 10 == 0) {
				ticketPrice.increasePrice();
			}
		}
		
		return ticket;
	}

	@Transactional
	@Override
	public Ticket pickWinningTicket() {
		Ticket ticket = null;
		
		synchronized (MUTEX) {
			List<Ticket> unpickedTickets = ticketRepository.findAllUnpickedTickets();
			if (unpickedTickets.isEmpty()) {
				throw new IllegalStateException("No more tickets available!");
			}
			Collections.shuffle(unpickedTickets);
			ticket = unpickedTickets.get(0);
			ticketRepository.updateTicketPicked(ticket.getId());
			ticket = getTicket(ticket.getId());
		}
		
		return ticket;
	}

	@Override
	public Ticket getTicket(Integer ticketId) {
		Optional<Ticket> ticket = ticketRepository.findById(ticketId);
		if(!ticket.isPresent()) {
			throw new IllegalArgumentException("Unknown Ticket Id! [id] = " + ticketId);
		}
		return ticket.get();
	}

	
	@Override
	public List<Ticket> getCurrentTicketsForUser(String user) {
		return ticketRepository.findAllUnpickedTicketsForUser(user);
	}

}
