package com.library.system.payloads;

import java.util.Date;

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
public class BooksDto {
	
	@NotEmpty(message = "Book name cannot be blank")
	@Size(min = 3, message = "Book name must be of minimum 3 characters") 
	@Size(max =  30,message = "Book name must be of maximum 30 characters")
	private String bookName;
	
	@NotEmpty(message = "Book Author cannot be blank")
	private String bookAuthor;
	private Date insertDate;
	private Date lastModifiedDate;
}
