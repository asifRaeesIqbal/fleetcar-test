package com.fleetcar.tombola;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class FleetcarTestApplication {
	
//	@Autowired
//	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(FleetcarTestApplication.class, args);
	}

	@PostConstruct
	private void initDb() {
//		System.out.println(String.format("****** Creating table: %s, and Inserting test data ******", "Tickets"));

//		for (int i = 1; i <= 300; i++) {
//			String sql = String.format("insert into ticket(id) values(%s)", i);
//			jdbcTemplate.execute(sql);
//		}
	}

//	@Bean(initMethod = "start", destroyMethod = "stop")
//	public Server inMemoryH2DatabaseServer() throws SQLException {
//		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9091");
//	}

}
