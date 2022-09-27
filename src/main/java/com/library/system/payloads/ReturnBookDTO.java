package com.library.system.payloads;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReturnBookDTO {

	private int bookId;
	private LocalDateTime insertDate;
	private LocalDateTime issuedDate;
	private LocalDateTime returnDate;
	private StudentsDataDto student;
}
