package com.library.system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.library.system.entities.ReturnBook;

@Repository
public interface ReturnBookRepo extends JpaRepository<ReturnBook, Integer> {
	@Query(value = "SELECT * FROM library_system_apis.return_book WHERE book_id = ? AND is_book_issued =0", nativeQuery = true)
	ReturnBook findByIdAndIsBookAvailable(int bookId);
	
	@Query(value = "SELECT * FROM library_system_apis.return_book WHERE book_id = ? AND is_book_issued =1", nativeQuery = true)
	ReturnBook findByIdAndReturnBook(int bookId);
}
