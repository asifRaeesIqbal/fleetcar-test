package com.fleetcar.tombola.web;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fleetcar.exception.ResourceNotFoundException;
import com.fleetcar.exception.UnprocessedEntityException;
import com.fleetcar.tombola.model.Ticket;
import com.fleetcar.tombola.model.TicketPrice;
import com.fleetcar.tombola.model.UserSummary;
import com.fleetcar.tombola.service.OddsCalculator;
import com.fleetcar.tombola.service.TicketService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * This is controller entry for buying tickets, as well as picking tickets and obtaining ticket information.
 * 
 * @author AI
 *
 */

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@Api(tags = "Ticket API")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private OddsCalculator oddsCalculatorService;
	
	@Autowired
	private TicketPrice ticketPrice;
	
	@ApiOperation(value = "Buy a new ticket.", response = Ticket.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 201, message = "Successfully bought a tombola ticket"),
	    @ApiResponse(code = 401, message = "You are not authorized to buy a ticket"),
	    @ApiResponse(code = 422, message = "No tickets are available.")
	})
	@PostMapping("/tombola/user/{user}/ticket/buy")
	public Ticket buyTicket(@PathVariable String user) throws UnprocessedEntityException {
		Ticket ticket;
		try {
			ticket = ticketService.buyTicket(user);
		} catch (IllegalStateException ex) {
			throw new UnprocessedEntityException(ex.getMessage());
		}
		return ticket;
	}
	
	@ApiOperation(value = "Retieves the list of tickets, and users current odds of winning(%)", response = UserSummary.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully retrieved odds and bought tickets."),
	    @ApiResponse(code = 401, message = "You are not authorized to get ticket information."),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
	})
	@GetMapping("/tombola/user/{user}/tickets")
	public UserSummary getMyTicketsAndOdds(@PathVariable String user) {
		List<Ticket> currentUserTickets = ticketService.getCurrentTicketsForUser(user);
		BigDecimal odds = oddsCalculatorService.getWinningOdds(user);
		
		UserSummary summary = new UserSummary();
		summary.setChancesOfWinning(odds);
		summary.setExistingTickets(currentUserTickets);
		return summary;
	}
	
	@ApiOperation(value = "Retrieves the current ticket price.", response = String.class)
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successfully retrieved current ticket price."),
		    @ApiResponse(code = 401, message = "You are not authorized to get ticket price.")
		})
	@GetMapping("/tombola/ticket/price")
	public String getCurrentTicketPrice() {
		return ticketPrice.getCurrentPrice();
	}
	
	@ApiOperation(value = "Retrieves the current ticket price.", response = Ticket.class)
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successfully retrieved specific ticket information."),
		    @ApiResponse(code = 401, message = "You are not authorized to get this ticket information."),
		    @ApiResponse(code = 404, message = "No tickets are available.")
		})
	@GetMapping("/tombola/ticket/{id}")
	public Ticket getTicket(@PathVariable Integer id) throws ResourceNotFoundException {
		Ticket ticket;
		try {
			ticket = ticketService.getTicket(id);
		} catch (IllegalArgumentException ex) {
			throw new ResourceNotFoundException(ex.getMessage());
		}
		return ticket;
	}
	
	@ApiOperation(value = "Pick a random ticket from tombola.", response = Ticket.class)
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successfully draw/pick a ticket out of tombola."),
		    @ApiResponse(code = 401, message = "You are not authorized to get pick a ticket.")
		})
	@GetMapping("/tombola/winning/ticket")
	public Ticket pickWinningTicket() {
		return ticketService.pickWinningTicket();
	}

}
