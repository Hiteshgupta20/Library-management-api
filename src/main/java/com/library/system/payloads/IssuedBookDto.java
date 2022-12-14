package com.library.system.payloads;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class IssuedBookDto {
	private int bookId;
	private String bookName;
	private String bookAuthor;
	private LocalDateTime insertDate;
	private LocalDateTime lastModifiedDate;
	private LocalDateTime issuedDate;
	private LocalDate returnDate;
	private StudentsDataDto student;
}
