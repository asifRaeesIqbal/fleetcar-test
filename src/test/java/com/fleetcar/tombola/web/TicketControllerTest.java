package com.fleetcar.tombola.web;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fleetcar.tombola.model.Ticket;
import com.fleetcar.tombola.model.TicketPrice;
import com.fleetcar.tombola.service.OddsCalculator;
import com.fleetcar.tombola.service.TicketService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TicketController.class)
public class TicketControllerTest {
	
	@MockBean
	private TicketService ticketService;

	@MockBean
	private OddsCalculator oddsCalculatorService;

	@MockBean
	private TicketPrice ticketPrice;

	@Autowired
	private MockMvc mockMvc;

	 @Test
	 public void test_buy_ticket_successfully() throws Exception {
	    Ticket ticket = new Ticket(1, false, false, "user1");
	    when(ticketService.buyTicket("user1")).thenReturn(ticket);
	    
	    this.mockMvc.perform(post("/tombola/user/user1/ticket/buy"))
	        .andDo(print())
	        .andExpect(status()
	          .isOk())
	        .andExpect(status().isOk())
	        .andExpect(content().string(containsString("{\"id\":1,\"picked\":false,\"bought\":false,\"user\":\"user1\"}")));
	  }

	 @Test
	 public void test_buy_ticket_unsuccessfully() throws Exception {
	    when(ticketService.buyTicket("user1")).thenThrow(new IllegalStateException("ERROR"));
	    
	    this.mockMvc.perform(post("/tombola/user/user1/ticket/buy"))
	        .andDo(print())
	        .andExpect(status().is4xxClientError());	  
	    }
}