package com.library.system.payloads;

import java.util.Date;

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

	private String studentName;
	private String course;
	private String password;
	private long msisdn;
	private String email;
	private Date resgistrationDate;
}
