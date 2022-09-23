package com.library.system.service.Impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.library.system.entities.Books;
import com.library.system.entities.Student;
import com.library.system.exceptions.ResourceNotFoundException;
import com.library.system.payloads.ApiRespone;
import com.library.system.payloads.BooksDto;
import com.library.system.payloads.IssuedBookDto;
import com.library.system.repositories.BooksRepo;
import com.library.system.repositories.StudentRepo;
import com.library.system.service.BooksService;
import com.library.system.util.constants.STUDENT;

@Service
public class BookServiceImpl implements BooksService {

	@Autowired
	private BooksRepo booksRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private StudentRepo studentRepo;

	@Override
	public ResponseEntity<ApiRespone> addBook(BooksDto booksDto) {
		Books books = modelMapper.map(booksDto, Books.class);
		books.setInsertDate(LocalDate.now());
		books.setLastModifiedDate(LocalDate.now());
		Books addBook = booksRepo.save(books);
		return ApiRespone.success(STUDENT.ADD_BOOK.getCode(), STUDENT.ADD_BOOK.getMessage(),
				modelMapper.map(addBook, BooksDto.class));
	}

	@Override
	public ResponseEntity<ApiRespone> updateBook(BooksDto booksDto, int bookId) {
		Books books = booksRepo.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "Id", bookId));
		books.setBookName(booksDto.getBookName());
		books.setBookAuthor(booksDto.getBookAuthor());
		books.setInsertDate(LocalDate.now());
		books.setLastModifiedDate(LocalDate.now());
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
	public ResponseEntity<ApiRespone> issueBook(int bookId,int studentId) {
		Books books = booksRepo.findByIdAndIsBookAvailable(bookId);
		System.out.println(books);
		if(books !=null && books.getStudent() ==null) {
			Student student = studentRepo.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
			books.setIssuedDate(LocalDate.now());
			books.setReturnDate(LocalDate.now().plusDays(7));
			books.setBookAvailabel(true);
			books.setStudent(student);
			Books issuedBooks = booksRepo.save(books);
			return ApiRespone.success(STUDENT.ISSUED_BOOK.getCode(), STUDENT.ISSUED_BOOK.getMessage(), modelMapper.map(issuedBooks, IssuedBookDto.class));
		
		}
		return ApiRespone.failure(STUDENT.BOOK_NOT_AVAILABEL.getCode(), STUDENT.BOOK_NOT_AVAILABEL.getMessage(), null);
		}

	@Override
	public ResponseEntity<ApiRespone> returnBook(int bookId) {
		// TODO Auto-generated method stub
		return null;
	}

}
