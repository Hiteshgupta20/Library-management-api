package com.library.system.util;

public class constants {
	
	public enum STUDENT{
		STUDENT_REGISTRATION(200,"Student Registered Successfully"),
		STUDENT_UPDATE(200,"Student Updated Successfully"),
		STUDENT_LIST(200,"Student Fetched Successfully"),
		ADD_BOOK(200,"Book Added Succesfully"),
		UPDATE_BOOK(200,"Book Updated Successfully"),
		BOOKS_LIST(200,"Books Fetched Successfully");


		
		
		private STUDENT(int code, String message) {
			this.code = code;
			this.message = message;
		}
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		private int code;
		private String message;
	}
	
}
