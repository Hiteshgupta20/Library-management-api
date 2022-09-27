package com.library.system.service;

import org.springframework.http.ResponseEntity;

import com.library.system.payloads.ApiRespone;
import com.library.system.payloads.BooksDto;

public interface BooksService {
	
	//Add Book
	ResponseEntity<ApiRespone> addBook(BooksDto booksDto);
	
	//Update Book
	ResponseEntity<ApiRespone> updateBook(BooksDto booksDto, int bookId);
	
	//Get Book By Id
	ResponseEntity<ApiRespone> getBookById(int bookId);
	
	//Get All Books
	ResponseEntity<ApiRespone> getAllBooks();
	
	//Issue Book
	ResponseEntity<ApiRespone> issueBook(int bookId, int studentId);
	
	//Return Book
	ResponseEntity<ApiRespone> returnBook(int bookId,int studentId);
	

}
