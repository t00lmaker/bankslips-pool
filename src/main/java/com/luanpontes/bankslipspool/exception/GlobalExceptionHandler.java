package com.luanpontes.bankslipspool.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Component
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleGenericErro(Exception ex) {
	  return "Internal api problem";
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY)
	public String handleArgumentError(MethodArgumentNotValidException exception) {
		String paramName = exception.getParameter().getParameterName();
		String message = "Invalid "+paramName+" provided. The possible reasons are: ";
		String errors = exception.getBindingResult().getFieldErrors()
				.stream()
				.map(erro -> erro.getField()+" "+erro.getDefaultMessage())
				.collect(Collectors.joining(", "));

		return  message+errors;
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public String handleMissingParams(HttpMessageNotReadableException exception) {
		return "param not provided in the request body";
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public String handleMissingParams(MethodArgumentTypeMismatchException exception) {
		return "invalid "+exception.getName()+ " provided - it must be a valid UUID";
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public String handleMissingParams(ResourceNotFoundException exception) {
		return exception.getResourceName()+" not found with the specified id: "+exception.getId();
	}
	
	

}

