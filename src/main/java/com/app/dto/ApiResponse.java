package com.app.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ApiResponse {
    //Time of Response
	private LocalDateTime timeStamp;
	//Response Message 
    private String message;
    public ApiResponse(String message) {
  	  super();
  	  this.message=message;
  	  this.timeStamp=LocalDateTime.now();
    }
}
