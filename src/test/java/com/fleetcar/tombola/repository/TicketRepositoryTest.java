package com.fleetcar.tombola.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fleetcar.tombola.model.Ticket;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql(scripts = {"/test.sql"}, config = @SqlConfig(encoding = "utf-8", transactionMode = TransactionMode.ISOLATED))
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class TicketRepositoryTest {
	
    @Autowired
    private TicketRepository ticketRepository;
    
    @Test
    public void find_all_unpicked_tickets_success() {
        // when
        List<Ticket> list = ticketRepository.findAllUnpickedTickets();
     
        // then
        assertThat(list.size()).isEqualTo(8);
    }
    
    @Test
    public void find_bought_tickets_success() {
        // when
        List<Ticket> list = ticketRepository.findAllBoughtTickets();
     
        // then
        assertThat(list.size()).isEqualTo(3);
    }

}
