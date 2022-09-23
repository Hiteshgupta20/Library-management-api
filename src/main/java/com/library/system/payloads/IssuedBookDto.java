package com.library.system.payloads;

import java.time.LocalDate;

import com.library.system.entities.Student;

import lombok.Data;

@Data
public class IssuedBookDto {
	private String bookName;
	private String bookAuthor;
	private LocalDate insertDate;
	private LocalDate lastModifiedDate;
	private LocalDate issuedDate;
	private LocalDate returnDate;
	private StudentDto student;
}
