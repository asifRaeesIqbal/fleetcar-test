package com.fleetcar.tombola.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fleetcar.tombola.model.Configuration;

public interface ConfigurationRespository extends JpaRepository<Configuration, String> {
	
}
