package com.library.system.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Books {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;
	private String bookName;
	private String bookAuthor;
	private Date insertDate;
	private Date lastModifiedDate;
}
