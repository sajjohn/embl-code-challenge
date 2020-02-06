package com.embl.api.personservice.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * @author sajjohn
 * Custom Error Handler Object
 *
 */
public class  ApiError {
	
	private HttpStatus status;
	private String message;
	private String errors;
	private List<String> errorList;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	
	
	public ApiError(HttpStatus httpStatus, String message, String errors, List<String> errorList) {
		super();
		this.timestamp = LocalDateTime.now();
		this.status = httpStatus;
		this.message = message;
		this.errors = errors;
		this.errorList = errorList;
	}

	public HttpStatus getHttpStatus() {
		return status;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.status = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

	public List<String> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}
	
	

	
}
