package com.fleetcar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessedEntityException extends Exception {

	public UnprocessedEntityException(String message) {
		super(message);
	}

}
