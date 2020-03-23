package com.fleetcar.tombola.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * This is a summary for a particular user of the tickets they currently have a chance of winning from and
 * the current odds.
 *  
 * @author AI
 *
 */

public class UserSummary {

	private BigDecimal chancesOfWinning;
	
	private List<Ticket> existingTickets;
	
	public BigDecimal getChancesOfWinning() {
		return chancesOfWinning;
	}
	
	public void setChancesOfWinning(BigDecimal chancesOfWinning) {
		this.chancesOfWinning = chancesOfWinning;
	}
	
	public List<Ticket> getExistingTickets() {
		return existingTickets;
	}
	
	public void setExistingTickets(List<Ticket> existingTickets) {
		this.existingTickets = existingTickets;
	}
	
	
}
