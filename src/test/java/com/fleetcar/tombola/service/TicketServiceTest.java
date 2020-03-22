package com.fleetcar.tombola.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fleetcar.tombola.model.Ticket;
import com.fleetcar.tombola.model.TicketPrice;
import com.fleetcar.tombola.repository.TicketRepository;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

	@Mock
	private TicketRepository ticketRepository;

	@Mock
	private TicketPrice ticketPrice;

	@InjectMocks
	private TicketService ticketService;

	@Test
	public void test_buy_ticket_successfully() {
		Ticket ticket = new Ticket(1, false, false, "");
		List<Ticket> availableTickets = Arrays.asList(ticket);

		Mockito.when(ticketRepository.findAllUnboughtAndUnpickedTickets()).thenReturn(availableTickets);
		Mockito.when(ticketRepository.findById(1)).thenReturn(Optional.of(ticket));

		Ticket boughtTicket = ticketService.buyTicket("123");

		assertNotNull(boughtTicket);
		assertEquals(1, boughtTicket.getId());

		Mockito.verify(ticketRepository).findAllUnboughtAndUnpickedTickets();
		Mockito.verify(ticketRepository).findById(1);

	}

	@Test
	public void test_buy_ticket_sold_out() {
		Mockito.when(ticketRepository.findAllUnboughtAndUnpickedTickets()).thenReturn(Arrays.asList());

		IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
			Ticket boughtTicket = ticketService.buyTicket("123");
		});
		assertEquals("No more tickets available!", exception.getMessage());
	}
	

}
