package com.app.exceptions;

@SuppressWarnings("serial")
public class OrderNotFoundException extends RuntimeException {
   
	 public OrderNotFoundException(String msg)
	 {
		 super(msg);
	 }
}
