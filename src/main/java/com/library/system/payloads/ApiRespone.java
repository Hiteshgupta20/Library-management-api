package com.library.system.payloads;

import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiRespone {

	private int statusCode;
	private String message;
	private Object object;

	
	public static ResponseEntity<ApiRespone> success(int statusCode,String message, Object object) {
		return ResponseEntity.ok().body(new ApiRespone(statusCode,message,object));
	}
	
	public static ResponseEntity<ApiRespone> failure(int statusCode,String message, Object object) {
		return ResponseEntity.ok().body(new ApiRespone(statusCode,message,object));
	}
}
