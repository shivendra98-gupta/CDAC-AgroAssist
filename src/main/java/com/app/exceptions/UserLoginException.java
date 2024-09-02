package com.app.exceptions;

@SuppressWarnings("serial")
public class UserLoginException extends RuntimeException{
   
	public UserLoginException(String msg)
	{
		super(msg);
	}
}
