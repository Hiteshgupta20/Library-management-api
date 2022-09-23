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
public class BooksDto {

	private String bookName;
	private String bookAuthor;
	private Date insertDate;
	private Date lastModifiedDate;
}
