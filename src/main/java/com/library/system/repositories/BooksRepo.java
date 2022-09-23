package com.library.system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.system.entities.Books;
@Repository
public interface BooksRepo extends JpaRepository<Books, Integer> {

}
