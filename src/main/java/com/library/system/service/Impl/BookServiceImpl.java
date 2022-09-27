package com.library.system.service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.library.system.entities.Books;
import com.library.system.entities.ReturnBook;
import com.library.system.entities.Student;
import com.library.system.exceptions.ResourceNotFoundException;
import com.library.system.payloads.ApiRespone;
import com.library.system.payloads.BooksDto;
import com.library.system.payloads.IssuedBookDto;
import com.library.system.payloads.ReturnBookDTO;
import com.library.system.payloads.StudentsDataDto;
import com.library.system.repositories.BooksRepo;
import com.library.system.repositories.ReturnBookRepo;
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

	@Autowired
	private ReturnBookRepo returnBookRepo;

	@Override
	public ResponseEntity<ApiRespone> addBook(BooksDto booksDto) {
		Books books = modelMapper.map(booksDto, Books.class);
		books.setInsertDate(LocalDateTime.now());
		books.setLastModifiedDate(LocalDateTime.now());
		Books addBook = booksRepo.save(books);
		this.addBookInReturnTable(addBook);
		return ApiRespone.success(STUDENT.ADD_BOOK.getCode(), STUDENT.ADD_BOOK.getMessage(),
				modelMapper.map(addBook, BooksDto.class));
	}

	private void addBookInReturnTable(Books addBook) {
		ReturnBook returnBook = new ReturnBook();
		returnBook.setBookId(addBook.getBookId());
		returnBook.setInsertDate(LocalDateTime.now());
		returnBook.setBookIssued(false);
		returnBookRepo.save(returnBook);
	}

	@Override
	public ResponseEntity<ApiRespone> updateBook(BooksDto booksDto, int bookId) {
		Books books = booksRepo.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "Id", bookId));
		books.setBookName(booksDto.getBookName());
		books.setBookAuthor(booksDto.getBookAuthor());
		books.setInsertDate(LocalDateTime.now());
		books.setLastModifiedDate(LocalDateTime.now());
		Books updatedBooks = booksRepo.save(books);
		return ApiRespone.success(STUDENT.UPDATE_BOOK.getCode(), STUDENT.UPDATE_BOOK.getMessage(),
				modelMapper.map(updatedBooks, IssuedBookDto.class));
	}

	@Override
	public ResponseEntity<ApiRespone> getBookById(int bookId) {
		Books books = booksRepo.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
		return ApiRespone.success(STUDENT.BOOKS_LIST.getCode(), STUDENT.BOOKS_LIST.getMessage(),
				modelMapper.map(books, IssuedBookDto.class));
	}

	@Override
	public ResponseEntity<ApiRespone> getAllBooks() {
		List<Books> books = booksRepo.findAll();
		List<IssuedBookDto> getAllBooks = books.stream().map(book -> modelMapper.map(book, IssuedBookDto.class))
				.collect(Collectors.toList());
		return ApiRespone.success(STUDENT.BOOKS_LIST.getCode(), STUDENT.BOOKS_LIST.getMessage(), getAllBooks);
	}

	@Override
	public ResponseEntity<ApiRespone> issueBook(int bookId, int studentId) {
		ReturnBook books = returnBookRepo.findByIdAndIsBookAvailable(bookId);
		System.out.println(books + "Books Find By Id");
		if (books != null && books.getStudent() == null) {
			Student student = studentRepo.findById(studentId)
					.orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
			System.err.println(modelMapper.map(student, StudentsDataDto.class));
			books.setIssuedDate(LocalDateTime.now());
			books.setReturnDate(LocalDateTime.now().plusDays(7));
			books.setBookIssued(true);
			books.setStudent(student);
			books.setBookId(bookId);
			books.setInsertDate(LocalDateTime.now());
			books.setStudentName(student.getStudentName());
			ReturnBook issuedBooks = returnBookRepo.save(books);
			return ApiRespone.success(STUDENT.ISSUED_BOOK.getCode(), STUDENT.ISSUED_BOOK.getMessage(),
					modelMapper.map(issuedBooks, ReturnBookDTO.class));

		}
		return ApiRespone.failure(STUDENT.BOOK_NOT_AVAILABEL.getCode(), STUDENT.BOOK_NOT_AVAILABEL.getMessage(), null);
	}

	@Override
	public ResponseEntity<ApiRespone> returnBook(int bookId, int studentId) {
		ReturnBook books = returnBookRepo.findByIdAndReturnBook(bookId);
		ReturnBookDTO issuedBook = modelMapper.map(books, ReturnBookDTO.class);
		Student student = studentRepo.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));

		System.out.println("Issued Book" + issuedBook);
		if (issuedBook.getStudent().getStudentId() == student.getStudentId() && issuedBook.getStudent() != null) {
			System.out.println("Inside If condition");
			books.setBookIssued(false);
			books.setIssuedDate(issuedBook.getIssuedDate());
			books.setReturnDate(LocalDateTime.now());
			books.setStudent(null);
			books.setStudentName(null);
			ReturnBook returnBook = returnBookRepo.save(books);
			return ApiRespone.success(STUDENT.RETURN_BOOK.getCode(), STUDENT.RETURN_BOOK.getMessage(),
					modelMapper.map(returnBook, ReturnBookDTO.class));

		}
		return ApiRespone.success(STUDENT.BOOK_NOT_AVAILABEL.getCode(), STUDENT.BOOK_NOT_AVAILABEL.getMessage(),
				modelMapper.map(null, ReturnBookDTO.class));
	}

}
