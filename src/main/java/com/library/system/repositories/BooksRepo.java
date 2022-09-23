package com.library.system.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.library.system.entities.Books;
@Repository
public interface BooksRepo extends JpaRepository<Books, Integer> {

	@Query(value = "SELECT * FROM library_system_apis.books WHERE book_id = ? AND is_book_availabel =0", nativeQuery = true)
	Books findByIdAndIsBookAvailable(int bookId);

}
