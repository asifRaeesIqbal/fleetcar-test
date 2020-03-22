package com.fleetcar.tombola.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "ticket")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "All details about a Ticket. ")
public class Ticket {

	@ApiModelProperty(notes = "The unique id of the ticket.")
	@Id
	private int id;

	@ApiModelProperty(notes = "Has this ticket been picked out")
	@NotNull
	private boolean picked;

	@ApiModelProperty(notes = "Has this ticket been bought by someone")
	@NotNull
	private boolean bought;

	@ApiModelProperty(notes = "Who bought this ticket - if at all.")
	private String user;

}
