package com.empcrud.emp.exception;

public class UserNotFoundException extends RuntimeException{
	public  UserNotFoundException(String message)
	{
		super(message);
	}
}
