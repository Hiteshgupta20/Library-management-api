package com.library.system.payloads;

import java.util.Date;

import lombok.Data;

@Data
public class StudentsDataDto {

	private int studentId;
	private String studentName;
	private String course;
	private String password;
	private long msisdn;
	private String email;
	private Date resgistrationDate;
}

