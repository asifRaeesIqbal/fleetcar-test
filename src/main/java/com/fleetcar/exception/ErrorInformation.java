package com.fleetcar.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorInformation {
	private Date timestamp;
	private String message;
	private String details;
}
