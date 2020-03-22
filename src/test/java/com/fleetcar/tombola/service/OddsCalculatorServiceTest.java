package com.fleetcar.tombola.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fleetcar.tombola.model.Ticket;
import com.fleetcar.tombola.repository.TicketRepository;

@ExtendWith(MockitoExtension.class)
public class OddsCalculatorServiceTest {

	@Mock
	private TicketRepository ticketRepository;

	@InjectMocks
	private OddsCalculatorService oddsCalculatorService;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_odds_are_valid() {
		BigDecimal expectedOdds = new BigDecimal("25.00");
		List<Ticket> availableTickets = Arrays.asList(new Ticket(), new Ticket(), new Ticket(), new Ticket());

		Mockito.when(ticketRepository.findAllUnboughtOrUnpickedTickets()).thenReturn(availableTickets);
		Mockito.when(ticketRepository.findAllUnpickedTicketsForUser("user")).thenReturn( Arrays.asList(new Ticket()));

		BigDecimal actualWinningOdds = oddsCalculatorService.getWinningOdds("user");

		assertEquals(expectedOdds, actualWinningOdds);

		Mockito.verify(ticketRepository).findAllUnboughtOrUnpickedTickets();

	}

}
