package com.luanpontes.bankslipspool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2026922867243031491L;

	private Long id;

	private String resourceName;

	public ResourceNotFoundException(Long id, String resourceName) {
		super();
		this.setId(id);
		this.setResourceName(resourceName);
	}
	public ResourceNotFoundException() {
		super();
	}
	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	public ResourceNotFoundException(String message) {
		super(message);
	}
	public ResourceNotFoundException(Throwable cause) {
		super(cause);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

}
