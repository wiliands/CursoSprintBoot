package br.com.springboot.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.springboot.exceptions.ExceptionResponse;
import br.com.springboot.exceptions.ResourceNotFoundException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
																	ex.getMessage(),
																	request.getDescription(false),
																	HttpStatus.INTERNAL_SERVER_ERROR.value(),
																	HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
																	ex.getMessage(),
																	request.getDescription(false),
																	HttpStatus.NOT_FOUND.value(),
																	HttpStatus.NOT_FOUND.getReasonPhrase());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

}
