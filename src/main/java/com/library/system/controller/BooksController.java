package com.library.system.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.system.payloads.ApiRespone;
import com.library.system.payloads.BooksDto;
import com.library.system.service.BooksService;

@RestController
@RequestMapping("/book/")
public class BooksController {

	@Autowired
	private BooksService booksService;
	
	@PostMapping("addBook")
	public ResponseEntity<ApiRespone> addBook(@Valid @RequestBody BooksDto booksDto){
		return booksService.addBook(booksDto);
	}
	
	@PutMapping("updateBook/{bookId}")
	public ResponseEntity<ApiRespone> updateBook(@Valid @RequestBody BooksDto booksDto, @PathVariable int bookId){
		return booksService.updateBook(booksDto, bookId);
	}
	
	@GetMapping("")
	public ResponseEntity<ApiRespone> getAllBooks(){
		return booksService.getAllBooks();
	}
	
	@GetMapping("{bookId}")
	public ResponseEntity<ApiRespone> getBookById(@PathVariable int bookId){
		return booksService.getBookById(bookId);
	}
	
	@PostMapping("issueBook/{bookId}/student/{studentId}")
	public ResponseEntity<ApiRespone> issueBook(@PathVariable int bookId, @PathVariable int studentId){
		return booksService.issueBook(bookId,studentId);
	}
}
