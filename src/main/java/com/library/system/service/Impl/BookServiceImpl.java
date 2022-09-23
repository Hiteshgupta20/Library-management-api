package com.library.system.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.library.system.entities.Books;
import com.library.system.exceptions.ResourceNotFoundException;
import com.library.system.payloads.ApiRespone;
import com.library.system.payloads.BooksDto;
import com.library.system.repositories.BooksRepo;
import com.library.system.service.BooksService;
import com.library.system.util.constants.STUDENT;

@Service
public class BookServiceImpl implements BooksService {

	@Autowired
	private BooksRepo booksRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<ApiRespone> addBook(BooksDto booksDto) {
		Books books = modelMapper.map(booksDto, Books.class);
		books.setInsertDate(new Date());
		books.setLastModifiedDate(new Date());
		Books addBook = booksRepo.save(books);
		return ApiRespone.success(STUDENT.ADD_BOOK.getCode(), STUDENT.ADD_BOOK.getMessage(),
				modelMapper.map(addBook, BooksDto.class));
	}

	@Override
	public ResponseEntity<ApiRespone> updateBook(BooksDto booksDto, int bookId) {
		Books books = booksRepo.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "Id", bookId));
		books.setBookName(booksDto.getBookName());
		books.setBookAuthor(booksDto.getBookAuthor());
		books.setInsertDate(new Date());
		books.setLastModifiedDate(new Date());
		Books updatedBooks = booksRepo.save(books);
		return ApiRespone.success(STUDENT.UPDATE_BOOK.getCode(), STUDENT.UPDATE_BOOK.getMessage(), updatedBooks);
	}

	@Override
	public ResponseEntity<ApiRespone> getBookById(int bookId) {
		Books books = booksRepo.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
		return ApiRespone.success(STUDENT.BOOKS_LIST.getCode(), STUDENT.BOOKS_LIST.getMessage(), books);
	}

	@Override
	public ResponseEntity<ApiRespone> getAllBooks() {
		List<Books> books = booksRepo.findAll();
		List<Books> getAllBooks = books.stream().map(book -> modelMapper.map(book, Books.class))
				.collect(Collectors.toList());
		return ApiRespone.success(STUDENT.BOOKS_LIST.getCode(), STUDENT.BOOKS_LIST.getMessage(), getAllBooks);
	}

	@Override
	public ResponseEntity<ApiRespone> issueBook(int bookId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ApiRespone> returnBook(int bookId) {
		// TODO Auto-generated method stub
		return null;
	}

}
