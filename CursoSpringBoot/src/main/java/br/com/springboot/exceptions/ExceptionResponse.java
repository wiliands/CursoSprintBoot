package br.com.springboot.exceptions;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date timestamp;
	private String mensage;
	private String details;
	private String httpStatus;
	private int httpCode;

	public ExceptionResponse(Date timestamp, String mensage, String details, int httpCode, String httpStatus) {
		super();
		this.timestamp = timestamp;
		this.mensage = mensage;
		this.details = details;
		this.httpCode = httpCode;
		this.httpStatus = httpStatus;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMensage() {
		return mensage;
	}

	public String getDetails() {
		return details;
	}

	public String getHttpStatus() {
		return httpStatus;
	}

	public int getHttpCode() {
		return httpCode;
	}

}
