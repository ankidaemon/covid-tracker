package com.xebia.covidtracker.exception;

public class InvalidRequestException extends RuntimeException {

	private static final long serialVersionUID = 5996408181763367451L;
	
	public InvalidRequestException(Exception e) {
		super(e);
	}

}
