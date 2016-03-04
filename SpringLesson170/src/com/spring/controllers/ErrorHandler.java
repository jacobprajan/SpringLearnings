package com.spring.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// this class can handle exceptions from all controllers

@ControllerAdvice
public class ErrorHandler
{
	@ExceptionHandler(DataAccessException.class)
	public String handleDatabaseException(DataAccessException ex)
	{
		ex.printStackTrace();
		return "error";
	}
	
	// Note: take care of importing spring exception
	@ExceptionHandler(AccessDeniedException.class)
	public String handleAccessDeniedException(AccessDeniedException ex)
	{
		ex.printStackTrace();
		return "denied";
	}
}
