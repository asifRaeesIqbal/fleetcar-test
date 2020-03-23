package com.fleetcar.tombola.model;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

/**
 * A singleton that stores the ticket price. This is incremented at every 100 tickets that are sold.
 * 
 * @author AI
 *
 */

@Component
public class TicketPrice {
	
	private Object MUTEX = new Object();
	
	private static BigDecimal CURRENT_PRICE = new BigDecimal("1.00").setScale(2);

	public String getCurrentPrice() {
		synchronized (MUTEX) {
			return CURRENT_PRICE.toString();
		}
	}
	
	public void increasePrice() {
		synchronized (MUTEX) {
			CURRENT_PRICE = CURRENT_PRICE.multiply(new BigDecimal("1.10").setScale(2)).setScale(2);
		}
	}
	
}
