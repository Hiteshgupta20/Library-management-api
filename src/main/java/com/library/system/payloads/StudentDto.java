package com.library.system.payloads;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
public class StudentDto {

	@NotEmpty(message = "Student Name must not be blank")
	@Size(min=3,message = "Student name must be of min 3 characters")
	@Size(max=30, message = "Student name must be of max 30 characters")
	private String studentName;
	private String course;
	private String password;
	private long msisdn;
	
	@Email(message = "Invalid Email")
	private String email;
	private Date resgistrationDate;
}
