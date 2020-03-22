package com.fleetcar.tombola.service;

import java.math.BigDecimal;

public interface OddsCalculator {
	
	/**
	 * Determines the chance of winning based on the number of tickets bought byt a user that have not been picked..
	 * 
	 * @param user
	 * @return
	 */
	BigDecimal getWinningOdds(String user);

}
