package com.library.system.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "return_book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReturnBook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int bookId;
	private String studentName;
	private LocalDateTime issuedDate;
	private LocalDateTime returnDate;
	private LocalDateTime insertDate;
	private boolean isBookIssued;
	@ManyToOne
	private Student student;
}

