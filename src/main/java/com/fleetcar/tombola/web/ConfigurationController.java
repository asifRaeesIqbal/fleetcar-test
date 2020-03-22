package com.fleetcar.tombola.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fleetcar.tombola.model.Configuration;
import com.fleetcar.tombola.repository.ConfigurationRespository;

import io.swagger.annotations.Api;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@Api(tags = "Configuration API")
public class ConfigurationController {

	@Autowired
	private ConfigurationRespository configurationRespository;
	
	@GetMapping("/tombola/configuration")
	public List<Configuration> getAllConfiguration() {
		return configurationRespository.findAll();
	}
	
	@GetMapping("/tombola/configuration/{id}")
	public Configuration getConfiguration(@PathVariable String id) {
		return configurationRespository.findById(id).get();
	}
	
}
