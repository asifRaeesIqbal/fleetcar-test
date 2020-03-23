package com.fleetcar.tombola.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fleetcar.tombola.model.Ticket;
import com.fleetcar.tombola.repository.TicketRepository;

/**
 * The class calculates the odds a user has of winning. This depends on the tickets the user has bought 
 * as well as the number of tickets left to be picked.
 *  
 * @author AI
 *
 */

@Service
public class OddsCalculatorService implements OddsCalculator {

	@Autowired
	private TicketRepository ticketRepository; 
	
	@Override
	public BigDecimal getWinningOdds(String user) {
		MathContext mc = new MathContext(2, RoundingMode.HALF_UP);

		List<Ticket> unboughtAndUnpickedTickets = ticketRepository.findAllUnboughtOrUnpickedTickets();
		List<Ticket> findAllUnpickedTicketsForUser = ticketRepository.findAllUnpickedTicketsForUser(user);
		
		Integer userTickets =  findAllUnpickedTicketsForUser.size();

		BigDecimal chancesOfNotWinning = new BigDecimal(userTickets).divide(new BigDecimal(unboughtAndUnpickedTickets.size()), mc);
		
		BigDecimal bd =  chancesOfNotWinning.multiply(new BigDecimal(100), mc).setScale(2);
		
		return bd;
	}

	public static void main(String args[]) {
		MathContext mc = new MathContext(2, RoundingMode.HALF_UP);
		BigDecimal bd = new BigDecimal(new Integer(300));
		BigDecimal chancesOfNotWinning = BigDecimal.ONE.divide(bd, mc);
		
		System.out.println("chancesOfNotWinning TICKETS " + chancesOfNotWinning);
		BigDecimal bd1 =  chancesOfNotWinning.multiply(new BigDecimal(100), mc).setScale(2, RoundingMode.HALF_UP);
		
		System.out.println(bd1);
	}
}
