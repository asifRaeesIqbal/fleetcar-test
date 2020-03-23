package com.fleetcar.tombola.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
/**
 * A simple entity class that maps to the configuration table of a data store. It is basically a 
 * key value table.
 * 
 * @author AI
 *
 */

@Table(name = "config")
@Entity
@Data
public class Configuration {

	@Id
	private String id;
	
	@NotNull
	private String value;

}
