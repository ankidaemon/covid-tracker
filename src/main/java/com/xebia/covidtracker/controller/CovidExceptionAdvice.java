package com.xebia.covidtracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.xebia.covidtracker.exception.InvalidLocationException;
import com.xebia.covidtracker.exception.InvalidRequestException;
import com.xebia.covidtracker.model.CovidDataResponse;

/**
 * ControllerAdvice
 * @author ankit.mishra@xebia.com
 *
 */
@ControllerAdvice
public class CovidExceptionAdvice {

	@ExceptionHandler({InvalidRequestException.class})
	public ResponseEntity<CovidDataResponse> handleInvalidReqException() {
		return new ResponseEntity<>(new CovidDataResponse("File format not supported. Please use specified CSV format"),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidLocationException.class)
	public ResponseEntity<CovidDataResponse> handleInvalidLocationException() {
		return new ResponseEntity<>(new CovidDataResponse("Location does not exist. Please upload initial data"),
				HttpStatus.BAD_REQUEST);
	}

}
